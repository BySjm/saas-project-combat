package cn.bysjm;


import cn.bysjm.quartz.BirthdayNotify;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestQuartz {
    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext-quartz.xml");
        app.start();
        new BirthdayNotify().birthdayNoti();
        System.in.read();
    }
}
