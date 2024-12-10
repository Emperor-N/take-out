package com.phn.mytakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phn.mytakeout.domain.po.SetmealDish;
import com.phn.mytakeout.domain.vo.SetmealVO;

import java.util.List;

public interface SetmealDishService extends IService<SetmealDish> {
    SetmealVO getSetmealDish(Long id);

    void addSetmealDish(List<SetmealDish> setmealDishes,Long id);

    void deleteByIds(Long id);
}
