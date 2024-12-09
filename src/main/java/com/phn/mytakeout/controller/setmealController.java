package com.phn.mytakeout.controller;

import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.Result.Result;
import com.phn.mytakeout.domain.dto.SetmealQueryDTO;
import com.phn.mytakeout.domain.po.Setmeal;
import com.phn.mytakeout.domain.vo.SetmealVO;
import com.phn.mytakeout.service.setmealDishService;
import com.phn.mytakeout.service.setmealService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@RequiredArgsConstructor
public class setmealController {

    private final setmealService setmealService;

    private final setmealDishService setmealDishService;

    @PostMapping("")
    @Transactional
    public Result addSetmeal(@RequestBody SetmealVO setmealVO) {
        setmealService.addSetmeal(setmealVO);
        Long id = setmealService.getSetmealIdByName(setmealVO.getName());
        setmealDishService.addSetmealDish(setmealVO.getSetmealDishes(),id);
        return Result.success();
    }

    @PutMapping("")
    public Result modifySetmeal(@RequestBody SetmealVO setmealVO) {
        setmealService.modifySetmeal(setmealVO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> getSetmealPage(SetmealQueryDTO setmealQueryDTO){
        return Result.success(setmealService.search(setmealQueryDTO));
    }

    @PostMapping("/search")
    public Result<PageResult> search(@RequestBody SetmealQueryDTO setmealQueryDTO){
        return Result.success(setmealService.searchByMsg(setmealQueryDTO));
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody List<Long> ids){
        setmealService.deleteByIds(ids);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result modifyStatus(@PathVariable ("status") Integer status, @RequestParam Long id){
        setmealService.modifyStatus(status,id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<SetmealVO> getSetmeal(@PathVariable Long id){

        SetmealVO hasSetmealDish = setmealDishService.getSetmealDish(id);

        Setmeal setmeal = setmealService.getSetmeal(id);

        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setmeal,setmealVO);
        setmealVO.setSetmealDishes(hasSetmealDish.getSetmealDishes());
        setmealVO.setUpdateTime(LocalDate.now());
        return Result.success(setmealVO);
    }

}
