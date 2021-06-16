package com.zhou.seven10.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.seven10.mapper.UserMapper;
import com.zhou.seven10.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

}
