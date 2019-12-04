package cn.bysjm;

import com.bysjm.util.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_mail.xml")
public class mailText {
    @Autowired
    private MailUtil mailUtil;

    @Test
    public void test() throws Exception {
        mailUtil.sendEmailWithAttachmentAndPic("1183781747@qq.com" , "spring整合邮件","看能不能收到信息！！！");
        System.out.println("收到");
    }

}
