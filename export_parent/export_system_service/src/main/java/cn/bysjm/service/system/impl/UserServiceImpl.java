package cn.bysjm.service.system.impl;

import cn.bysjm.dao.system.UserDao;
import cn.bysjm.domain.system.User;
import cn.bysjm.domain.system.User;
import cn.bysjm.service.system.UserService;
import cn.bysjm.utils.HttpUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private String appid = "wx3bdb1192c22883f3";
    private String secret = "db9d6b88821df403e5ff11742e799105";
    private String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private String wxInfoUrl = "https://api.weixin.qq.com/sns/userinfo";

    @Autowired
    private UserDao userDao;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<User> notifyList(String date) {
        return userDao.notifyList(date);
    }

    @Override
    public List<User> findAll(String companyId) {
        return userDao.findAll(companyId);
    }

    @Override
    public PageInfo findPage(Integer page, Integer pageSize, String companyId) {
        PageHelper.startPage(page, pageSize);
        List<User> list = userDao.findAll(companyId);
        return new PageInfo(list, 5);
    }

    @Override
    public void save(User user) {
        String password_old = user.getPassword();
        String password = user.getPassword();
        password = new Md5Hash(password, user.getEmail(), 2).toString();//密文密码
        user.setPassword(password);
        user.setCreateTime(new Date());
        userDao.save(user);//用户保存成功
        //使用rabbitMQ发送一封邮件
        Map<String, String> map = new HashMap<>();
        map.put("to", user.getEmail());
        map.put("subject", "e53e9a80-1434-11ea-a6da-525400e00ae3 代表 SAAS货代云平台");
        map.put("content", "亲爱的"+user.getUserName()+"，你的密码是" + password_old);
        amqpTemplate.convertAndSend("user.insert", map);
    }

    @Override
    public User toUpdate(String id) {
        return userDao.findById(id);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(new Date());
        userDao.update(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public List<String> findRoleIdsByUserId(String id) {
        return userDao.findRoleIdsByUserId(id);
    }

    @Override
    public void changeRole(String userid, String[] roleIds) {
        userDao.deleteRoleById(userid);
        for (String roleId : roleIds) {
            userDao.updateRole(userid, roleId);
        }
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User weixinlogin(String code) {
        User user = null;
        //1.根据code获取access_token和openId
        String atUtl = accessTokenUrl + "?code="+code+"&appid="+appid+"&secret="+secret+"&grant_type=authorization_code";
        System.out.println(atUtl);
        Map<String, Object> map1 = HttpUtils.sendGet(atUtl);
        Object access_token = map1.get("access_token");
        Object openid = map1.get("openid").toString();
        if(access_token == null && openid == null) {
            return user;
        }
        //2.根据openId判断用户是否存在
        user = userDao.findByUnionid(openid.toString());
        if(user != null) {
            System.out.println("返回数据库中的用户对象");
            //3.如果用户存在返回用户信息
            return user;
        }else {
            user = new User();
            user.setUnionid(openid.toString());
        }
        return user;
    }

    @Override
    public User bindMail(User user) {//这个user有邮箱，密码，unionid
        String password = new Md5Hash(user.getPassword(), user.getEmail(), 2).toString();//加密后的密码
        User userFromDB = userDao.findByEmail(user.getEmail());
        if (password.equals(userFromDB.getPassword())){
            userDao.updateByEmail(userFromDB.getEmail(),user.getUnionid());
            return userFromDB;
        }
        return null;
    }

    public static void main(String[] args) {
        String password = new Md5Hash("123456", "laowang@export.com", 2).toString();
        System.out.println(password);
    }
}
