package com.jms.forum.controller;

import com.jms.forum.dto.Result;
import com.jms.forum.dto.UploadDto;
import com.jms.forum.dto.UserDto;
import com.jms.forum.entity.User;
import com.jms.forum.service.UserService;
import com.jms.forum.utils.UpPhotoNameUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/15 21:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserDto login(User user){
        return userService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping("/upload")
    @CrossOrigin()
    public UploadDto singleFileUpload(@RequestParam("file") MultipartFile file,
                                      @RequestParam("id") Integer id) {
        System.out.println(id);
        try {
            byte[] bytes = file.getBytes();
            String imageFileName = file.getOriginalFilename();
            String fileName = UpPhotoNameUtils.getPhotoName("img",imageFileName);
            Path path = Paths.get("D:\\ideaWorkSpace\\javaEE分组项目\\forum_vue\\src\\assets\\images\\img\\" + fileName);
            //“C:\\框架\\D4\\d4_pc_ui\\src\\assets\\images\\img\\”为本地目录
            Files.write(path, bytes);//写入文件
            String avatar_url=fileName;
            userService.updateAvatar(id, fileName);
            return new UploadDto(true, fileName);//返回文件名字
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new UploadDto(false, "");
    }
}
