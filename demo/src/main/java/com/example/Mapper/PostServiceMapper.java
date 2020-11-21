package com.example.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostServiceMapper {
    List<Map<String, String>> queryPost(@Param("userUuid") String userUuid,
                                        @Param("tag") String tag,
                                        @Param("from") Integer from,
                                        @Param("offset") Integer offset);

    void deletePost(@Param("userUuid") String userUuid,
                    @Param("uuid") String uuid);

    void createPost(@Param("userUuid") String userUuid,
                    @Param("title") String title,
                    @Param("description") String description,
                    @Param("tag") String tag,
                    @Param("type") Integer type,
                    @Param("uuid") String uuid,
                    @Param("filePath") String filePath);

    void updatePost(@Param("userUuid") String userUuid,
                    @Param("uuid") String uuid,
                    @Param("title") String title,
                    @Param("description") String description,
                    @Param("tag") String tag,
                    @Param("filePath") String filePath);
}
