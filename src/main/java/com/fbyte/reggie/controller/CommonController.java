package com.fbyte.reggie.controller;

import com.fbyte.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;


/**
 * @author FHJ
 * @version 1.0
 * @description 文件的上传和下载
 * @className CommonController
 * @date 2023/2/24 24
 * @since 1.0
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     *
     * @param file 参数名要与前台name一致
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        //file是一个临时文件,需要转存到指定文件，否则本次请求完成后自动被删除
        log.info(file.toString());

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID重新生成文件名
        String fileName = UUID.randomUUID() + suffix;

        //创建一个目录对象
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.success(fileName);
    }


    /**
     * 文件下载
     *
     * @param name     文件名
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {

        ServletOutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        try {
            //输入流,通过输入流来读取文件内容
            fileInputStream = new FileInputStream(new File(basePath + name));
            //输出流,通过输出流将文件写会浏览器
            outputStream = response.getOutputStream();

            response.setContentType("image/jepg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
