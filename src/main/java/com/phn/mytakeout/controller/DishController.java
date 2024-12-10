package com.phn.mytakeout.controller;

import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.Result.Result;
import com.phn.mytakeout.domain.dto.AddAndModifyDishDTO;
import com.phn.mytakeout.domain.dto.DishQueryDTO;
import com.phn.mytakeout.domain.po.Dish;
import com.phn.mytakeout.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping("/list")
    public Result<List<Dish>> getDishList(@RequestParam Long categoryId) {
        return Result.success(dishService.getDishList(categoryId));
    }

    @PostMapping("/modify")
    public Result modifyDish(@RequestBody AddAndModifyDishDTO ModifyDishDTO){
        dishService.modifyDish(ModifyDishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> getDishPage(DishQueryDTO dishQueryDTO){
        return Result.success(dishService.search(dishQueryDTO));
    }

    @PostMapping("/search")
    public Result<PageResult> search(@RequestBody DishQueryDTO dishQueryDTO){
        return Result.success(dishService.searchByMsg(dishQueryDTO));
    }

    @PostMapping("/status/{status}")
    public Result modifyStatus(@PathVariable ("status") Integer status, @RequestParam Long id){
        dishService.modifyStatus(status,id);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody List<Long> ids){
        dishService.deleteByIds(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Dish> getDishById(@PathVariable Long id){
        return Result.success(dishService.getDishById(id));
    }

    @PostMapping("")
    public Result addDish(@RequestBody AddAndModifyDishDTO addDishDTO){
        dishService.addDish(addDishDTO);
        return Result.success();
    }

}
