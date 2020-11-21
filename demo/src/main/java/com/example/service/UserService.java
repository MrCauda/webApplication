package com.example.service;


import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String, String>> queryUserByUserName(String username);

    void deleteUser(String uuid);

    void createUser(String username, Integer gender, String habitm, String remarks, Integer premium, String picUrl, String password);

    String updateUser(String uuid, String username, Integer gender, String habitm, Integer premium, String remarks, String picUrl, String oldPassword, String newPassword, String updateType);
}
