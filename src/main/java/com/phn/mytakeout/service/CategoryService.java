package com.phn.mytakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phn.mytakeout.Result.PageResult;
import com.phn.mytakeout.domain.dto.AddAndModifyCategoryDTO;
import com.phn.mytakeout.domain.dto.CategoryQueryDTO;
import com.phn.mytakeout.domain.po.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    void addCategory(AddAndModifyCategoryDTO addAndModifyCategory);

    PageResult search(CategoryQueryDTO categoryQueryDTO);

    void deleteByIds(List<Long> ids);

    Category getCategoryById(Long id);

    void modifyCategory(AddAndModifyCategoryDTO addAndModifyCategoryDTO);

    void modifyStatus(int status, Long id);

    PageResult searchByMsg(CategoryQueryDTO categoryQueryDTO);

    List<Category> listByType(Integer type);
}
