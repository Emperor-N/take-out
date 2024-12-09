package com.phn.mytakeout.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phn.mytakeout.domain.po.SetmealDish;
import com.phn.mytakeout.domain.vo.SetmealVO;
import com.phn.mytakeout.mapper.setmealDishMapper;
import com.phn.mytakeout.service.setmealDishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class setmealDishServiceImpl extends ServiceImpl<setmealDishMapper, SetmealDish> implements setmealDishService {
    
    private final setmealDishMapper setmealDishMapper;
    
    @Override
    public SetmealVO getSetmealDish(Long id) {

        List<SetmealDish> list = lambdaQuery().eq(SetmealDish::getSetmealId, id).list();
        SetmealVO setmealVO = new SetmealVO();
        setmealVO.setSetmealDishes(list);

        return setmealVO;
    }

    @Override
    public void addSetmealDish(List<SetmealDish> setmealDishes,Long setmealId) {
        for(SetmealDish s:setmealDishes){
            s.setSetmealId(setmealId);
            setmealDishMapper.insert(s);
        }
    }

    @Override
    public void deleteByIds(Long id) {
        setmealDishMapper.deleteBySetmealId(id);
    }
}
