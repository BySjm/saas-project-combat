package cn.bysjm.email;

import cn.bysjm.utils.MailUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.util.Map;

public class EmailConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        byte[] body = message.getBody();
        Map<String,String> map = JSON.parseObject(body, Map.class);
        String to = map.get("to");
        String subject = map.get("subject");
        String content = map.get("content");
        try {
            MailUtil.sendMsg(to,"bysjmjl@163.com我们去玩把bysjmjl@163.com"+subject+"bysjmjl@163.com我们去玩把bysjmjl@163.com",content+content+content+content);
            System.out.printf("邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
