package com.fbyte.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fbyte.reggie.common.R;
import com.fbyte.reggie.entity.User;
import com.fbyte.reggie.service.UserService;
import com.fbyte.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author FHJ
 * @version 1.0
 * @description 用户管理
 * @className UserController
 * @date 2023/2/27 27
 * @since 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;


    @Resource
    private JavaMailSender javaMailSender;//我们需要用这个进行邮件发送
    //注意这里我们将发送者从配置文件注入进来
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 邮箱验证码发送
     *
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/sendMsg")
    private R<String> sendMsg(@RequestBody User user, HttpSession session) {
        //这里用qq邮箱去发送验证码
        //获取到前端提交过来的qq号
        String phone = user.getPhone();
        //这里工具类判是否为空
        if (StringUtils.isNotEmpty(phone)) {
            //这里用到生成验证码的工具类
            String code = ValidateCodeUtils.generateValidateCode(4).toString();//生成四位的验证码
            log.info("code={}", code);
            //构建一个邮件的对象
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //设置邮件发件者
            simpleMailMessage.setFrom(from);
            //设置邮件接受者
            simpleMailMessage.setTo(phone);
            //设置有纪念的主题
            simpleMailMessage.setSubject("登录验证码");
            //设置邮件的征文
            String text = "(reggie)给您的验证码为: " + code + " 请勿泄露";
            simpleMailMessage.setText(text);

            //将生成的验证码保存到Session将我们生成的手机号和验证码放到session里面，我们后面用户填入验证码之后，我们验证的时候就从这里去取然后进行比对
            //这里我们需要一个异常捕获
            //session.setAttribute(phone, code);

            //将验证码放在redis中，设置有效期5分钟
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            //return Rsuccess("手机验证码短信发送成功");

            try {
                javaMailSender.send(simpleMailMessage);
                return R.success("验证码短信发送成功");
            } catch (MailException e) {
                e.printStackTrace();
            }
        }
        return R.error("验证码发送失败");
    }


    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        log.info("map:{}", map);

        //获取邮箱
        String phone = map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();
        //从session中获取保存的验证码
        //Object codeInSession = session.getAttribute(phone);

        //从redis获取
        Object codeInSession = redisTemplate.opsForValue().get(phone);
        //进行验证码的比比对
        if (codeInSession != null && codeInSession.equals(code)) {
            //比对成功，登录成功
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                //判断当前邮箱是否在用户表中,为新用户自动注册
                user = new User();
                user.setPhone(phone);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());

            //用户登录成功，删除Redis中缓存的验证码
            redisTemplate.delete(phone);
            return R.success(user);

        }

        return R.error("登录失败");
    }

    /**
     * 退出功能
     * ①在controller中创建对应的处理方法来接受前端的请求，请求方式为post；
     * ②清理session中的用户id
     * ③返回结果（前端页面会进行跳转到登录页面）
     * @return
     */
    @PostMapping("/loginout")
    public R<String> logout(HttpServletRequest request){
        //清理session中的用户id
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }
}

