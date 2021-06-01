package com.zhou.five.pojo;

import com.zhou.five.annotation.MyCache;
import lombok.Data;

import java.util.List;

@Data
public class Klass {
    private List<Student> students;

    @MyCache(5)
    public String dong(){
        System.out.println(getStudents());
        return "dong";
    }
}
