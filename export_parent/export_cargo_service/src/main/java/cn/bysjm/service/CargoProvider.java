package cn.bysjm.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class CargoProvider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        app.start();
        System.in.read();
    }
}
