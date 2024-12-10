package com.phn.mytakeout.controller;

import com.phn.mytakeout.Result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("admin/common")
public class CommentController {

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        return Result.success("https://phn-myproject.oss-cn-beijing.aliyuncs.com/02b2189d-ae51-47b7-a414-bd6c9d26f385..png");
    }

}
