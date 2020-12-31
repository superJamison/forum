package com.jms.forum.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.jms.forum.config.KaptchaConfig;
import com.jms.forum.dto.Result;
import com.jms.forum.dto.UploadDto;
import com.jms.forum.dto.UserDto;
import com.jms.forum.entity.User;
import com.jms.forum.service.UserService;
import com.jms.forum.utils.MD5Utils;
import com.jms.forum.utils.UpPhotoNameUtils;
import com.jms.forum.utils.VerifyCodeUtils;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/15 21:36
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @PostMapping("/login")
    public UserDto login(User user, String validation,
                         HttpServletRequest request){
         String code = (String) request.getServletContext().getAttribute("code");
         code = code.toLowerCase();
         validation = validation.toLowerCase();
        if (!code.equals(validation) || validation == null){
            UserDto userDto = new UserDto();
            userDto.setLogin(false);
             userDto.setMessage("验证码错误！请重新输入");
            return userDto;
        }
        return userService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping("/getUserByToken")
    public UserDto getUserByToken(String token){
        User user = userService.getUserByToken(token);
        UserDto userDto = new UserDto();
        userDto.setLogin(true);
        userDto.setUser(user);
        userDto.setMessage("登录成功！");
        return userDto;
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

    //用户注册
    @PostMapping("/add")
    public Result add(User user){
        try {
            user.setToken( UUID.randomUUID().toString());
            user.setAvatarUrl("default.jpg");
            user.setPassword(MD5Utils.inputPassToDbPass(user.getPassword(), user.getUsername()));
            List<String> allUser = userService.getAllUser();
            for (String s : allUser) {
                if (s.equals(user.getUsername())){
                    return new Result(false, "注册失败!用户名已存在！");
                }
            }
            userService.add(user);
            return new Result(true, "注册成功!可以登录啦！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "注册失败!重新注册！");
        }
    }

    /**
     * 生成验证码图片
     */
    @GetMapping("getImage")
    public String getImageCode(HttpServletRequest request)throws IOException{
        // 1.使用工具类生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        // 2.将验证码放入servletContext作用域
        request.getServletContext().setAttribute("code",code);
        // 3.将图片转为字节数组输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(220,60,byteArrayOutputStream,code);

        // 4.将字节数组输出流编码为base64返回
        return "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());

    }
}
