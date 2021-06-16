package com.zhou.seven10;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhou.seven10.pojo.User;
import com.zhou.seven10.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Seven10ApplicationTests {

    @Autowired
    IUserService userService;

    @Test
    public void insert1() {
        User user = new User();
        user.setId(1);
        user.setName("tom");
        user.setAge(15);
        userService.saveOrUpdate(user);
    }
    @Test
    public void insert2(){
        User user = new User();
        user.setId(2);
        user.setName("lily");
        user.setAge(16);
        userService.saveOrUpdate(user);
    }
    @Test
    public void select1(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        List<User> list = userService.list(userQueryWrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }

}
