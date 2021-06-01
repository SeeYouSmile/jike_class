package com.zhou.five.pojo;

import com.zhou.five.aop.ITeacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements ITeacher {

    private String name;

    @Override
    public void lecture(String subject) {
        System.out.println(name+"老师开始授课，科目是："+subject);
    }
}
