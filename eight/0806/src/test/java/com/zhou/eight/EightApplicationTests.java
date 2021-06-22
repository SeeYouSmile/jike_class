package com.zhou.eight;

import com.zhou.eight.service.JtaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EightApplication.class)
public class EightApplicationTests {

    @Autowired
    private JtaService jtaService;

    @Test
    public void testInsert() {
        jtaService.testInsert();
    }

    @Test
    public void testSelect(){
        jtaService.testSelect();
    }

}
