package com.phn.mytakeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phn.mytakeout.domain.dto.AddAndModifyCategory;
import com.phn.mytakeout.domain.po.Category;

public interface categoryService  extends IService<Category> {
    void addCategory(AddAndModifyCategory addAndModifyCategory);
}
