package com.phn.mytakeout.controller;

import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.Result.Result;
import com.phn.mytakeout.domain.dto.AddAndModifyCategoryDTO;
import com.phn.mytakeout.domain.dto.CategoryQueryDTO;
import com.phn.mytakeout.domain.po.Category;
import com.phn.mytakeout.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public Result<Category> addCategory(@RequestBody AddAndModifyCategoryDTO addAndModifyCategory){
        categoryService.addCategory(addAndModifyCategory);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> getCategoryPage(CategoryQueryDTO categoryQueryDTO){
        return Result.success(categoryService.search(categoryQueryDTO));
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody List<Long> ids){
        categoryService.deleteByIds(ids);
        return Result.success();
    }

    @PostMapping("/search")
    public Result<PageResult> search(@RequestBody CategoryQueryDTO categoryQueryDTO){
        return Result.success(categoryService.searchByMsg(categoryQueryDTO));
    }

    @GetMapping("/{id}")
    public Result<Category> getCategoryById(@PathVariable Long id){
        return Result.success(categoryService.getCategoryById(id));
    }

    @PostMapping("/modify")
    public Result modifyCategory(@RequestBody AddAndModifyCategoryDTO ModifyCategoryDTO){
        categoryService.modifyCategory(ModifyCategoryDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result modifyStatus(@PathVariable ("status") int status, @RequestParam Long id){
        categoryService.modifyStatus(status,id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Category>> getCategoryList(@RequestParam Integer type){
        return Result.success(categoryService.listByType(type));
    }

}
