package com.phn.mytakeout.controller;

import com.phn.mytakeout.Result.Result;
import com.phn.mytakeout.domain.dto.AddAndModifyCategory;
import com.phn.mytakeout.domain.po.Category;
import com.phn.mytakeout.service.categoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class categoryController {

    private final categoryService categoryService;

    @PostMapping("")
    public Result<Category> addCategory(@RequestBody AddAndModifyCategory addAndModifyCategory){
        categoryService.addCategory(addAndModifyCategory);
        return Result.success();
    }
}
