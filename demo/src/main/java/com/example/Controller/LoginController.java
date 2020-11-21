package com.example.Controller;

import com.example.Res.GeneralResult;
import com.example.Res.Result;
import com.example.Utils.MD5;
import com.example.login.User;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.Utils.MD5.generateMd5;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public GeneralResult login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        String password = requestUser.getPassword();

        List<Map<String, String>> users = new ArrayList<>();
        users = userService.queryUserByUserName(username);

        if (users.isEmpty()) {
            return GeneralResult.create("用户名不存在", "failed");
        } else {
            for (Map<String, String> user : users) {
                String md5Password = generateMd5(password);
                String sortedPassword = user.get("password");
                assert md5Password != null;
                if (md5Password.equals(sortedPassword)) {
                    logger.error("login succeed");
                    return GeneralResult.create("登陆成功", "succeed");
                } else {
                    logger.error("login failed");
                    return GeneralResult.create("账号密码错误", "failed");
                }
            }
        }
        return null;
    }
}