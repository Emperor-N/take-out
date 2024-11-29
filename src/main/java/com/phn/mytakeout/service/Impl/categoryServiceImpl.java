package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.AddAndModifyCategoryDTO;
import com.phn.mytakeout.domain.dto.CategoryQueryDTO;
import com.phn.mytakeout.domain.po.Category;
import com.phn.mytakeout.domain.po.Employee;
import com.phn.mytakeout.mapper.categoryMapper;
import com.phn.mytakeout.service.categoryService;
import com.phn.mytakeout.utils.ThreadLocalUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class categoryServiceImpl extends ServiceImpl<categoryMapper, Category> implements categoryService {

    private final categoryMapper categoryMapper;

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

        // 设置结果
        PageResult pageResult = new PageResult();
        pageResult.setTotal(result.getTotal());
        pageResult.setRecord(result.getRecords());
        return pageResult;
    }
}
