package com.phn.mytakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.DishQueryDTO;
import com.phn.mytakeout.domain.po.Dish;
import com.phn.mytakeout.domain.dto.AddAndModifyDishDTO;

import java.util.List;

public interface DishService extends IService<Dish> {
    List<Dish> getDishList(Long categoryId);

    PageResult search(DishQueryDTO dishQueryDTO);

    PageResult searchByMsg(DishQueryDTO dishQueryDTO);

    void modifyStatus(Integer status, Long id);

    void deleteByIds(List<Long> ids);

    Dish getDishById(Long id);

    void modifyDish(AddAndModifyDishDTO addAndModifyDishDTO);

    void addDish(AddAndModifyDishDTO addDishDTO);
}
