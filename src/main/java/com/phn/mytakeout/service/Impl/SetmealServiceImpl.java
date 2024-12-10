package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.SetmealQueryDTO;
import com.phn.mytakeout.domain.po.Employee;
import com.phn.mytakeout.domain.po.Setmeal;
import com.phn.mytakeout.domain.vo.SetmealVO;
import com.phn.mytakeout.mapper.EmployeeMapper;
import com.phn.mytakeout.mapper.SetmealMapper;
import com.phn.mytakeout.service.CategoryService;
import com.phn.mytakeout.service.SetmealDishService;
import com.phn.mytakeout.service.SetmealService;
import com.phn.mytakeout.utils.ThreadLocalUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    private final SetmealMapper setmealMapper;
    private final SetmealDishService setmealDishService;
    private final CategoryService categoryService;
    private final EmployeeMapper employeeMapper;

    @Override
    public PageResult search(SetmealQueryDTO setmealQueryDTO) {

        Page<Setmeal> page = new Page<>(setmealQueryDTO.getPage(),setmealQueryDTO.getPageSize());

        Page<Setmeal> result = lambdaQuery().page(page);

        for(Setmeal s:result.getRecords()){
            s.setCategoryName(categoryService.getById(s.getCategoryId()).getName());
        }

        Map<Long, String> createMap = new HashMap<>();
        Map<Long, String> updateMap = new HashMap<>();
        for(Setmeal setmeal:result.getRecords()){
            Employee createUserInfo = employeeMapper.selectById(setmeal.getCreateUser());
            Employee updateUserInfo = employeeMapper.selectById(setmeal.getUpdateUser());
            if(createMap.get(setmeal.getCreateUser()) != null){
                setmeal.setCreateUserName(createMap.get(setmeal.getCreateUser()));
            }else{
                createMap.put(setmeal.getCreateUser(), createUserInfo.getUsername());
                setmeal.setCreateUserName(createMap.get(setmeal.getCreateUser()));
            }

            if(updateMap.get(setmeal.getUpdateUser()) != null){
                setmeal.setUpdateUserName(createMap.get(setmeal.getUpdateUser()));
            }else{
                updateMap.put(setmeal.getUpdateUser(), updateUserInfo.getUsername());
                setmeal.setUpdateUserName(updateMap.get(setmeal.getUpdateUser()));
            }
        }

        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotal());
        pageResult.setRecord(result.getRecords());

        return pageResult;
    }

    @Override
    public PageResult searchByMsg(SetmealQueryDTO setmealQueryDTO) {

        Page<Setmeal> page = new Page<>(setmealQueryDTO.getPage(),setmealQueryDTO.getPageSize());

        Page<Setmeal> result = lambdaQuery()
                .like(StringUtils.isNotBlank(setmealQueryDTO.getName()),Setmeal::getName,setmealQueryDTO.getName())
                .eq(setmealQueryDTO.getStatus() != null,Setmeal::getStatus,setmealQueryDTO.getStatus())
                .eq(setmealQueryDTO.getType() != null,Setmeal::getCategoryId,setmealQueryDTO.getType())
                .page(page);


        for(Setmeal s:result.getRecords()){
            s.setCategoryName(categoryService.getById(s.getCategoryId()).getName());
        }

        Map<Long, String> createMap = new HashMap<>();
        Map<Long, String> updateMap = new HashMap<>();
        for(Setmeal setmeal:result.getRecords()){
            Employee createUserInfo = employeeMapper.selectById(setmeal.getCreateUser());
            Employee updateUserInfo = employeeMapper.selectById(setmeal.getUpdateUser());
            if(createMap.get(setmeal.getCreateUser()) != null){
                setmeal.setCreateUserName(createMap.get(setmeal.getCreateUser()));
            }else{
                createMap.put(setmeal.getCreateUser(), createUserInfo.getUsername());
                setmeal.setCreateUserName(createMap.get(setmeal.getCreateUser()));
            }

            if(updateMap.get(setmeal.getUpdateUser()) != null){
                setmeal.setUpdateUserName(createMap.get(setmeal.getUpdateUser()));
            }else{
                updateMap.put(setmeal.getUpdateUser(), updateUserInfo.getUsername());
                setmeal.setUpdateUserName(updateMap.get(setmeal.getUpdateUser()));
            }
        }

        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotal());
        pageResult.setRecord(result.getRecords());

        return pageResult;
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        setmealMapper.deleteBatchIds(ids);
    }

    @Override
    public void modifyStatus(int status, Long id) {
        lambdaUpdate().eq(Setmeal::getId,id).set(Setmeal::getStatus,status).update();
    }

    @Override
    public Setmeal getSetmeal(Long id) {
        return setmealMapper.selectById(id);
    }

    @Override
    public void addSetmeal(SetmealVO setmealVO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealVO,setmeal);
        setmeal.setCreateTime(LocalDateTime.now());
        setmeal.setUpdateTime(LocalDateTime.now());
        setmeal.setCreateUser(ThreadLocalUserContext.getUser());
        setmeal.setUpdateUser(ThreadLocalUserContext.getUser());
        setmealMapper.insert(setmeal);
    }

    @Override
    @Transactional
    public void modifySetmeal(SetmealVO setmealVO) {
        setmealMapper.deleteById(setmealVO.getId());
        setmealDishService.deleteByIds(setmealVO.getId());

        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealVO,setmeal);
        setmeal.setCreateTime(LocalDateTime.now());
        setmeal.setUpdateTime(LocalDateTime.now());
        setmeal.setCreateUser(ThreadLocalUserContext.getUser());
        setmeal.setUpdateUser(ThreadLocalUserContext.getUser());
        setmealMapper.insert(setmeal);
        Long id = this.getSetmealIdByName(setmealVO.getName());
        setmealDishService.addSetmealDish(setmealVO.getSetmealDishes(),id);

    }

    @Override
    public Long getSetmealIdByName(String name) {
        return lambdaQuery().eq(Setmeal::getName,name).one().getId();
    }
}
