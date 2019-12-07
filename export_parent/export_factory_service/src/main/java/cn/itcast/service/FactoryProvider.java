package cn.itcast.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class FactoryProvider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        app.start();
        System.in.read();
    }
}
