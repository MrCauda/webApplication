package com.example.service.Impl;

import com.example.Mapper.UserServiceMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.example.Utils.MD5.generateMd5;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserServiceMapper userServiceMapper;

    @Override
    public List<Map<String, String>> queryUserByUserName(String username){
        List<Map<String, String>> users = userServiceMapper.queryUserByUserName(username);
        return users;
    }

    @Override
    public void deleteUser(String uuid){
        userServiceMapper.deleteUser(uuid);
    }

    @Override
    public void createUser(String username, Integer gender, String habit, String remarks, Integer premium, String picUrl, String passward){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String md5Password = generateMd5(passward);
        userServiceMapper.createUser(username, gender, habit, remarks, premium, uuid, picUrl, md5Password);
    }

    @Override
    public String updateUser(String uuid, String username, Integer gender, String habit, Integer premium, String remarks, String picUrl, String oldPassword, String newPassword, String updateType){
        if (updateType.equals("1")) {
            userServiceMapper.updateUserInfo(uuid, username, gender, habit, premium, remarks, picUrl);
            return "修改完成";
        } else if (updateType.equals("2")) {
            String md5Password = generateMd5(oldPassword);
            String sortedPassword = userServiceMapper.getPassword(uuid);
            assert md5Password != null;
            if (md5Password.equals(sortedPassword)) {
                String newPasswordMd5 = generateMd5(newPassword);
                userServiceMapper.updateUserPassword(uuid, newPasswordMd5);
                return "修改完成";
            } else {
                return "密码不正确";
            }
        }
        return null;
    }
}
