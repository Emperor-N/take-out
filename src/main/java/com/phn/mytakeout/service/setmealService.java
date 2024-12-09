package com.phn.mytakeout.service;

import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.SetmealQueryDTO;
import com.phn.mytakeout.domain.po.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.phn.mytakeout.domain.vo.SetmealVO;

import java.util.List;

public interface setmealService extends IService<Setmeal>{
    PageResult search(SetmealQueryDTO setmealQueryDTO);

    PageResult searchByMsg(SetmealQueryDTO setmealQueryDTO);

    void deleteByIds(List<Long> ids);

    void modifyStatus(int status, Long id);

    Setmeal getSetmeal(Long id);

    void addSetmeal(SetmealVO setmealVO);

    void modifySetmeal(SetmealVO setmealVO);

    Long getSetmealIdByName(String name);
}

