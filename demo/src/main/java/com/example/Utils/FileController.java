package com.example.Utils;

import com.example.Res.GeneralResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@PropertySource("classpath:custom.properties")
@RequestMapping("/file")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${custom.imagePath}")
    private String imagePath;

    @PostMapping(value = "/image/upload")
    public GeneralResult uploadImage(@RequestParam(name = "uploadFile") MultipartFile file) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String fullFileName = uuid + "-" + file.getOriginalFilename();
        Path path = Paths.get(imagePath, fullFileName);  //在配置文件中定义；文件实际的存储地址

        System.out.println("save image to path: " + path.toString());
        File parentFile = path.getParent().toFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
            logger.info("parent directory create success:" + parentFile.getPath());
        }
        try {
            file.transferTo(path.toFile());
            logger.info("file upload success :" + path.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("save image error:" + e);
            return GeneralResult.create(e.getMessage(), "failed");
        }
        return GeneralResult.create(path.toString());
    }

    @GetMapping(value = "/image/show", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> showImage(@RequestParam(name = "fileName") String fileName) throws FileNotFoundException {
        Path path = Paths.get(fileName);  //在配置文件中定义；文件实际的存储地址
//        Path path = Paths.get("/Users/chenxin/Downloads/2019050916405554.jpg.png");
        System.out.println("get: " + path.toString());
        File file = path.toFile();
        InputStream inputStream = new FileInputStream(file);
        InputStreamResource resource = new InputStreamResource(inputStream);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}


