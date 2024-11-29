package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.domain.dto.AddAndModifyCategory;
import com.phn.mytakeout.domain.po.Category;
import com.phn.mytakeout.domain.po.Dish;
import com.phn.mytakeout.mapper.categoryMapper;
import com.phn.mytakeout.mapper.dishMapper;
import com.phn.mytakeout.service.categoryService;
import com.phn.mytakeout.service.dishService;
import com.phn.mytakeout.utils.ThreadLocalUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class categoryServiceImpl extends ServiceImpl<categoryMapper, Category> implements categoryService {

    private final categoryMapper categoryMapper;


    @Override
    public void addCategory(AddAndModifyCategory addAndModifyCategory) {
        Category category = new Category();
        BeanUtils.copyProperties(addAndModifyCategory,category);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(ThreadLocalUserContext.getUser());
        category.setUpdateUser(ThreadLocalUserContext.getUser());
        category.setStatus(1);
        categoryMapper.insert(category);
    }
}
