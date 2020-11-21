package com.example.Controller;

import com.example.Res.GeneralResult;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/queryUser")
    @ResponseBody
    public GeneralResult queryUser(@RequestParam(value = "username") String username) {
        List<Map<String, String>> users = new ArrayList<>();
        try {
            users = userService.queryUserByUserName(username);
        } catch (Exception e) {
            return GeneralResult.create(e.getMessage(), "failed");
        }
        return GeneralResult.create(users);
    }

    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    public GeneralResult deleteUser(@RequestParam(value = "uuid") String uuid) {
        try {
            userService.deleteUser(uuid);
        } catch (Exception e) {
            return GeneralResult.create(e.getMessage(), "failed");
        }
        return GeneralResult.Success();
    }

    @RequestMapping(value = "/createUser")
    @ResponseBody
    public GeneralResult createUser(@RequestParam(value = "username") String username,
                                    @RequestParam(value = "gender") Integer gender,
                                    @RequestParam(value = "habit", required = false) String habit,
                                    @RequestParam(value = "picUrl", required = false) String picUrl,
                                    @RequestParam(value = "remarks", required = false) String remarks,
                                    @RequestParam(value = "premium", required = false) Integer premium,
                                    @RequestParam(value = "password") String password) {
        try {
            userService.createUser(username, gender, habit, remarks, premium, picUrl, password);
        } catch (Exception e) {
            return GeneralResult.create(e.getMessage(), "failed");
        }
        return GeneralResult.Success();
    }

    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public GeneralResult updateUser(@RequestParam(value = "uuid") String uuid,
                                    @RequestParam(value = "username", required = false) String username,
                                    @RequestParam(value = "gender", required = false) Integer gender,
                                    @RequestParam(value = "habit", required = false) String habit,
                                    @RequestParam(value = "picUrl", required = false) String picUrl,
                                    @RequestParam(value = "premium", required = false) Integer premium,
                                    @RequestParam(value = "remarks", required = false) String remarks,
                                    @RequestParam(value = "oldPassword", required = false) String oldPassword,
                                    @RequestParam(value = "newPassword", required = false) String newPassword,
                                    @RequestParam(value = "updateType") String updateType) {
        try {
            if (updateType.equals("1")) {
                // 修改普通内容
                if (username == null && gender == null && habit == null && remarks == null && picUrl == null) {
                    return GeneralResult.create("未填写需要修改的内容", "failed");
                }
            } else if (updateType.equals("2")) {
                if (oldPassword == null) {
                    return GeneralResult.create("未填写旧密码", "failed");
                } else if (newPassword == null) {
                    return GeneralResult.create("未填写新密码", "failed");
                }

            }
            String res = userService.updateUser(uuid, username, gender, habit, premium, remarks, picUrl, oldPassword, newPassword, updateType);
            if (res.equals("密码不正确")) {
                return GeneralResult.create(res, "failed");
            }
        } catch (Exception e) {
            return GeneralResult.create(e.getMessage(), "failed");
        }
        return GeneralResult.Success();
    }
}
