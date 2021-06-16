package com.zhou.seven09.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.seven09.annotation.CurDataSource;
import com.zhou.seven09.datasource.DataSourceNames;
import com.zhou.seven09.mapper.UserMapper;
import com.zhou.seven09.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService{

    //不加注解默认第一个数据库
    @Override
    public List<User> selectUser() {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        return baseMapper.selectList(wrapper);
    }

    //第二个数据库
    @CurDataSource(DataSourceNames.SECOND)
    @Override
    public List<User> selectUser2() {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        return baseMapper.selectList(wrapper);
    }
}
