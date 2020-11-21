package com.example.service;


import java.util.List;
import java.util.Map;

public interface PostService {
    List<Map<String, String>> queryPost(String userUuid, String tag, Integer pageNo, Integer pageSize);

    void deletePost(String userUuid, String uuid);

    void createPost(String userUuid, String title, String description, String tag, Integer type, String filePath);

    void updatePost(String userUuid, String uuid, String title, String description, String tag, String filePath);
}
