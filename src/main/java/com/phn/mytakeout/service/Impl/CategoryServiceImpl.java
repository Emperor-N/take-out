package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.AddAndModifyCategoryDTO;
import com.phn.mytakeout.domain.dto.CategoryQueryDTO;
import com.phn.mytakeout.domain.po.Category;
import com.phn.mytakeout.domain.po.Employee;
import com.phn.mytakeout.mapper.CategoryMapper;
import com.phn.mytakeout.mapper.EmployeeMapper;
import com.phn.mytakeout.service.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final CategoryMapper categoryMapper;

    private final EmployeeMapper employeeMapper;

    @Override
    public void addCategory(AddAndModifyCategoryDTO addAndModifyCategory) {
        Category category = new Category();
        BeanUtils.copyProperties(addAndModifyCategory,category);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(ThreadLocalUserContext.getUser());
        category.setUpdateUser(ThreadLocalUserContext.getUser());
        category.setStatus(1);
        categoryMapper.insert(category);
    }

    @Override
    public PageResult search(CategoryQueryDTO categoryQueryDTO) {
        Page<Category> page = new Page<Category>(categoryQueryDTO.getPage(),categoryQueryDTO.getPageSize());

        Page<Category> result = lambdaQuery().page(page);

        Map<Long, String> createMap = new HashMap<>();
        Map<Long, String> updateMap = new HashMap<>();
        for(Category category:result.getRecords()){
            Employee createUserInfo = employeeMapper.selectById(category.getCreateUser());
            Employee updateUserInfo = employeeMapper.selectById(category.getUpdateUser());
            if(createMap.get(category.getCreateUser()) != null){
                category.setCreateUserName(createMap.get(category.getCreateUser()));
            }else{
                createMap.put(category.getCreateUser(), createUserInfo.getUsername());
                category.setCreateUserName(createMap.get(category.getCreateUser()));
            }

            if(updateMap.get(category.getUpdateUser()) != null){
                category.setUpdateUserName(createMap.get(category.getUpdateUser()));
            }else{
                updateMap.put(category.getUpdateUser(), updateUserInfo.getUsername());
                category.setUpdateUserName(updateMap.get(category.getUpdateUser()));
            }
        }

        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotal());
        pageResult.setRecord(result.getRecords());

        return pageResult;
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        categoryMapper.deleteBatchIds(ids);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryMapper.selectById(id);
    }

    @Override
    @Transactional
    public void modifyCategory(AddAndModifyCategoryDTO modifyCategoryDTO) {
        Category one = categoryMapper.selectById(modifyCategoryDTO.getId());
        categoryMapper.deleteById(modifyCategoryDTO.getId());

        Category category = new Category();
        BeanUtils.copyProperties(one,category);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(ThreadLocalUserContext.getUser());
        category.setName(modifyCategoryDTO.getName());
        category.setDescription(modifyCategoryDTO.getDescription());
        category.setType(modifyCategoryDTO.getType());
        category.setImage(modifyCategoryDTO.getImage());
        category.setStatus(1);

        categoryMapper.insert(category);
    }

    @Override
    public void modifyStatus(int status, Long id) {
        lambdaUpdate().eq(Category::getId,id).set(Category::getStatus,status).update();
    }

    @Override
    public PageResult searchByMsg(CategoryQueryDTO categoryQueryDTO) {
        Page<Category> page = new Page<>(categoryQueryDTO.getPage(), categoryQueryDTO.getPageSize());

        // 创建查询条件并执行分页查询
        Page<Category> result = lambdaQuery()
                .like(StringUtils.isNotBlank(categoryQueryDTO.getName()), Category::getName, categoryQueryDTO.getName())
                .eq(categoryQueryDTO.getStatus() != null, Category::getStatus, categoryQueryDTO.getStatus())
                .lt(categoryQueryDTO.getEndTime() != null, Category::getCreateTime, categoryQueryDTO.getEndTime())
                .gt(categoryQueryDTO.getStartTime() != null, Category::getCreateTime, categoryQueryDTO.getStartTime())
                .page(page);

        Map<Long, String> createMap = new HashMap<>();
        Map<Long, String> updateMap = new HashMap<>();
        for(Category category:result.getRecords()){
            Employee createUserInfo = employeeMapper.selectById(category.getCreateUser());
            Employee updateUserInfo = employeeMapper.selectById(category.getUpdateUser());
            if(createMap.get(category.getCreateUser()) != null){
                category.setCreateUserName(createMap.get(category.getCreateUser()));
            }else{
                createMap.put(category.getCreateUser(), createUserInfo.getUsername());
                category.setCreateUserName(createMap.get(category.getCreateUser()));
            }

            if(updateMap.get(category.getUpdateUser()) != null){
                category.setUpdateUserName(createMap.get(category.getUpdateUser()));
            }else{
                updateMap.put(category.getUpdateUser(), updateUserInfo.getUsername());
                category.setUpdateUserName(updateMap.get(category.getUpdateUser()));
            }
        }
        // 设置结果
        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotal());
        pageResult.setRecord(result.getRecords());
        return pageResult;
    }

    @Override
    public List<Category> listByType(Integer type) {
        return lambdaQuery().eq(Category::getType,type).list();
    }
}
