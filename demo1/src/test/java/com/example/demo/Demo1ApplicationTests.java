package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1ApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void test(){
        String s = " ";
       String[] s1 = s.split(" ");
        System.out.println(s1.length);
       for(String s2:s1){
           System.out.println(s2);

       }
    }
}
