package com.example.service.Impl;

import com.example.Mapper.PostServiceMapper;
import com.example.Mapper.UserServiceMapper;
import com.example.service.PostService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostServiceMapper postServiceMapper;

    @Override
    public List<Map<String, String>> queryPost(String userUuid, String tag, Integer pageNo, Integer pageSize) {
        Integer from = (pageNo - 1) * pageSize;
        List<Map<String, String>> posts = postServiceMapper.queryPost(userUuid, tag, from, pageSize);
        return posts;
    }

    @Override
    public void deletePost(String userUuid, String uuid) {
        postServiceMapper.deletePost(userUuid, uuid);
    }

    @Override
    public void createPost(String userUuid, String title, String description, String tag, Integer type, String filePath) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        postServiceMapper.createPost(userUuid, title, description, tag, type, uuid, filePath);
    }

    @Override
    public void updatePost(String userUuid, String uuid, String title, String description, String tag, String filePath) {
        postServiceMapper.updatePost(userUuid, uuid, title, description, tag, filePath);
    }
}
