package cn.bysjm.quartz;

import cn.bysjm.domain.system.User;
import cn.bysjm.service.system.UserService;
import cn.bysjm.utils.MailUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BirthdayNotify {

    @Autowired
    private UserService userService;

    public void birthdayNoti() throws Exception {
        //当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        String nowDate = dateFormat.format(new Date());
        //获取当天生日的用户
        List<User> notifyList = userService.notifyList(nowDate);
        for (User user : notifyList) {
            //生日
            String[] birthdayStr = user.getBirthday().split("-");
            String birDate = birthdayStr[1] + "-" + birthdayStr[2];
            if (nowDate.equals(birDate)) {
                //发送一封邮件
                MailUtil.sendMsg(user.getEmail(), "bysjmjl@163.com我们去玩把bysjmjl@163.com", "你好bysjmjl@163.com");
                System.out.println("邮件发送成功");
            }
        }

    }

}
