package com.example.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserServiceMapper {
    List<Map<String, String>> queryUserByUserName(@Param("username") String username);

    void deleteUser(@Param("uuid") String uuid);

    void createUser(@Param("username") String username,
                    @Param("gender") Integer gender,
                    @Param("habit") String habit,
                    @Param("remarks") String remarks,
                    @Param("premium") Integer premium,
                    @Param("uuid") String uuid,
                    @Param("picUrl") String picUrl,
                    @Param("password") String password);

    void updateUserInfo(@Param("uuid") String uuid,
                    @Param("username") String username,
                    @Param("gender") Integer gender,
                    @Param("habit") String habit,
                    @Param("premium") Integer premium,
                    @Param("remarks") String remarks,
                    @Param("picUrl") String picUrl);

    void updateUserPassword(@Param("uuid") String uuid,
                    @Param("newPassword") String newPassword);

    String getPassword(@Param("uuid") String uuid);
}
