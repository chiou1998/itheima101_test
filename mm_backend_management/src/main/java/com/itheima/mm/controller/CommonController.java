package com.itheima.mm.controller;

import com.itheima.mm.entity.Result;
import com.itheima.mm.utils.UploadUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * 包名:com.itheima.mm.controller
 *
 * @author Leevi
 * 日期2020-09-15  14:39
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @RequestMapping(value = "/upload")
    public Result uploadFile(HttpSession session,MultipartFile upload) throws Exception {
        //1. 获取到客户端上传的图片
        try {
            //upload就是客户端上传的文件
            //准备一个目录存储客户端上传的文件
            String realPath = session.getServletContext().getRealPath("img/upload");
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            //获取上传的文件名
            String uuidName = UploadUtils.getUUIDName(upload.getOriginalFilename());

            upload.transferTo(new File(file,uuidName));

            //向客户端响应文件存储的路径
            String imgUrl = "img/upload/"+uuidName;
            return new Result(true,"图片上传成功",imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"图片上传失败");
        }
    }
}
