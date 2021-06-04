package com.nefu.myspringboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.myspringboot.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    default User getByNumber(String number) {
        return selectOne(new LambdaQueryWrapper<User>().eq(User::getNumber, number));
    }
}
