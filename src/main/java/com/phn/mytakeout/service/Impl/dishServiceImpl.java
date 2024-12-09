package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.AddAndModifyDishDTO;
import com.phn.mytakeout.domain.dto.DishQueryDTO;
import com.phn.mytakeout.domain.po.Dish;
import com.phn.mytakeout.domain.po.Employee;
import com.phn.mytakeout.domain.po.Setmeal;
import com.phn.mytakeout.mapper.dishMapper;
import com.phn.mytakeout.mapper.employeeMapper;
import com.phn.mytakeout.service.categoryService;
import com.phn.mytakeout.service.dishService;
import com.phn.mytakeout.utils.ThreadLocalUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class dishServiceImpl extends ServiceImpl<dishMapper, Dish> implements dishService {

    private final dishMapper dishMapper;

    private final employeeMapper employeeMapper;

    private final categoryService categoryService;

    @Override
    public List<Dish> getDishList(Long categoryId) {
        List<Dish> list = lambdaQuery().eq(Dish::getCategoryId, categoryId).list();
        return list;
    }

    @Override
    public PageResult search(DishQueryDTO dishQueryDTO) {
        Page<Dish> page = new Page<>(dishQueryDTO.getPage(), dishQueryDTO.getPageSize());

        Page<Dish> result = lambdaQuery()
                .page(page);

        for(Dish s:result.getRecords()){
            s.setCategoryName(categoryService.getById(s.getCategoryId()).getName());
        }

        Map<Long, String> createMap = new HashMap<>();
        Map<Long, String> updateMap = new HashMap<>();
        for(Dish dish:result.getRecords()){
            Employee createUserInfo = employeeMapper.selectById(dish.getCreateUser());
            Employee updateUserInfo = employeeMapper.selectById(dish.getUpdateUser());
            if(createMap.get(dish.getCreateUser()) != null){
                dish.setCreateUserName(createMap.get(dish.getCreateUser()));
            }else{
                createMap.put(dish.getCreateUser(), createUserInfo.getUsername());
                dish.setCreateUserName(createMap.get(dish.getCreateUser()));
            }

            if(updateMap.get(dish.getUpdateUser()) != null){
                dish.setUpdateUserName(createMap.get(dish.getUpdateUser()));
            }else{
                updateMap.put(dish.getUpdateUser(), updateUserInfo.getUsername());
                dish.setUpdateUserName(updateMap.get(dish.getUpdateUser()));
            }
        }

        // 设置结果
        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotal());
        pageResult.setRecord(result.getRecords());

        return pageResult;
    }

    @Override
    public PageResult searchByMsg(DishQueryDTO dishQueryDTO) {
        Page<Dish> page = new Page<>(dishQueryDTO.getPage(), dishQueryDTO.getPageSize());

        // 创建查询条件并执行分页查询
        Page<Dish> result = lambdaQuery()
                .like(StringUtils.isNotBlank(dishQueryDTO.getName()), Dish::getName, dishQueryDTO.getName())
                .eq(dishQueryDTO.getStatus() != null,Dish::getStatus,dishQueryDTO.getStatus())
                .eq(dishQueryDTO.getCategoryId() != null,Dish::getCategoryId,dishQueryDTO.getCategoryId())
                .page(page);

        for(Dish s:result.getRecords()){
            s.setCategoryName(categoryService.getById(s.getCategoryId()).getName());
        }

        Map<Long, String> createMap = new HashMap<>();
        Map<Long, String> updateMap = new HashMap<>();
        for(Dish dish:result.getRecords()){
            Employee createUserInfo = employeeMapper.selectById(dish.getCreateUser());
            Employee updateUserInfo = employeeMapper.selectById(dish.getUpdateUser());
            if(createMap.get(dish.getCreateUser()) != null){
                dish.setCreateUserName(createMap.get(dish.getCreateUser()));
            }else{
                createMap.put(dish.getCreateUser(), createUserInfo.getUsername());
                dish.setCreateUserName(createMap.get(dish.getCreateUser()));
            }

            if(updateMap.get(dish.getUpdateUser()) != null){
                dish.setUpdateUserName(createMap.get(dish.getUpdateUser()));
            }else{
                updateMap.put(dish.getUpdateUser(), updateUserInfo.getUsername());
                dish.setUpdateUserName(updateMap.get(dish.getUpdateUser()));
            }
        }

        // 设置结果
        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotal());
        pageResult.setRecord(result.getRecords());
        return pageResult;
    }

    @Override
    public void modifyStatus(Integer status, Long id) {
        lambdaUpdate().eq(Dish::getId,id).set(Dish::getStatus,status).update();
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        dishMapper.deleteBatchIds(ids);
    }

    @Override
    public Dish getDishById(Long id) {
        Dish dish = dishMapper.selectById(id);
        dish.setCategoryName(categoryService.getById(dish.getCategoryId()).getName());
        return dish;
    }

    @Override
    public void modifyDish(AddAndModifyDishDTO ModifyDishDTO) {
        Dish one = dishMapper.selectById(ModifyDishDTO.getId());
        dishMapper.deleteById(ModifyDishDTO.getId());

        Dish dish = new Dish();
        dish.setUpdateTime(LocalDateTime.now());
        dish.setCreateTime(one.getCreateTime());
        dish.setUpdateUser(ThreadLocalUserContext.getUser());
        dish.setCreateUser(one.getCreateUser());
        BeanUtils.copyProperties(ModifyDishDTO,dish);
        dishMapper.insert(dish);
    }

    @Override
    public void addDish(AddAndModifyDishDTO addDishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(addDishDTO,dish);
        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dish.setCreateUser(ThreadLocalUserContext.getUser());
        dish.setUpdateUser(ThreadLocalUserContext.getUser());
        dish.setStatus(1);
        dishMapper.insert(dish);
    }
}
