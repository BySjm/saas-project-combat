package cn.bysjm.quartz;

import cn.bysjm.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class BirthdayNotify {

    @Autowired
    private UserService userService;

    public void birthdayNoti(){
        List<Date> birthday = userService.findBirthday();
        System.out.println(birthday);
    }

}
