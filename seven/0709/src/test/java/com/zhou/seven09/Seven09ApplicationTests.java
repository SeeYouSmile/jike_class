package com.zhou.seven09;

import com.zhou.seven09.pojo.User;
import com.zhou.seven09.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Seven09ApplicationTests {

    @Autowired
    IUserService userService;

    @Test
    public void test() {
        List<User> users = userService.selectUser();
        System.out.println("第一个数据库查询结果：");
        for (User user : users) {
            System.out.println(user);
        }
        List<User> users2 = userService.selectUser2();
        System.out.println("第二个数据库查询结果：");
        for (User user : users2) {
            System.out.println(user);
        }
    }

}
