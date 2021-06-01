package com.zhou.five.pojo;

import com.zhou.five.aop.ISchool;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@ToString
public class School implements ISchool {

    @Autowired
    private Klass class1;

    @Resource(name = "student100")
    private Student student100;

    @Override
    public void ding() {
        System.out.println("class1 have "+class1.getStudents().size()+" students and one student100 "+student100.toString());
    }
}
