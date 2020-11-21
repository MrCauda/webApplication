package com.example.Controller;

import com.example.Res.GeneralResult;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "post")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(value = "/createPost")
    @ResponseBody
    public GeneralResult createPost(@RequestParam(value = "userUuid") String userUuid,
                                    @RequestParam(value = "title") String title,
                                    @RequestParam(value = "description", required = false) String description,
                                    @RequestParam(value = "tag", required = false) String tag,
                                    @RequestParam(value = "type", required = false) Integer type,
                                    @RequestParam(value = "filePath", required = false) String filePath) {
        try {
            postService.createPost(userUuid, title, description, tag, type, filePath);
        } catch (Exception e) {
            return GeneralResult.create(e.getMessage(), "failed");
        }
        return GeneralResult.Success();
    }

    @RequestMapping(value = "/queryPost")
    @ResponseBody
    public GeneralResult queryUser(@RequestParam(value = "userUuid", required = false) String userUuid,
                                   @RequestParam(value = "tag", required = false) String tag,
                                   @RequestParam(value = "pageNo") Integer pageNo,
                                   @RequestParam(value = "pageSize") Integer pageSize) {
        List<Map<String, String>> posts = new ArrayList<>();
        try {
            posts = postService.queryPost(userUuid, tag, pageNo, pageSize);
        } catch (Exception e) {
            return GeneralResult.create(e.getMessage(), "failed");
        }
        return GeneralResult.create(posts);
    }

    @RequestMapping(value = "/deletePost")
    @ResponseBody
    public GeneralResult deletePost(@RequestParam(value = "userUuid") String userUuid,
                                    @RequestParam(value = "uuid") String uuid) {
        try {
            postService.deletePost(userUuid, uuid);
        } catch (Exception e) {
            return GeneralResult.create(e.getMessage(), "failed");
        }
        return GeneralResult.Success();
    }

    @RequestMapping(value = "/updatePost")
    @ResponseBody
    public GeneralResult updatePost(@RequestParam(value = "userUuid") String userUuid,
                                    @RequestParam(value = "uuid") String uuid,
                                    @RequestParam(value = "title", required = false) String title,
                                    @RequestParam(value = "description", required = false) String description,
                                    @RequestParam(value = "tag", required = false) String tag,
                                    @RequestParam(value = "filePath", required = false) String filePath) {
        try {
            if (title == null && description == null && tag == null && filePath == null) {
                return GeneralResult.create("未填写需要修改的内容", "failed");
            }
            postService.updatePost(userUuid, uuid, title, description, tag, filePath);
        } catch (Exception e) {
            return GeneralResult.create(e.getMessage(), "failed");
        }
        return GeneralResult.Success();
    }
}
