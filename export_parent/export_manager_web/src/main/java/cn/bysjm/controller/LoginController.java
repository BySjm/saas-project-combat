package cn.bysjm.controller;


import cn.bysjm.domain.system.Module;
import cn.bysjm.domain.system.User;
import cn.bysjm.service.system.ModuleService;
import cn.bysjm.service.system.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private ModuleService moduleService;

    @RequestMapping("/login")
    public String login(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            request.setAttribute("error", "用户名或密码不能为空");
            return "forward:login.jsp";
        }
        password = new Md5Hash(password, email, 2).toString();
        return method(email, password);
    }

    //微信登录
    @RequestMapping(value = "/weixinlogin", name = "用户登出")
    public String weixinlogin(String code) {
        User user = userService.weixinlogin(code);
        if (user == null) {
            request.setAttribute("error", "微信登录失败，请重试");
            return "forward:/login.jsp";
        } else if (user.getId() == null) {
            request.setAttribute("unionid", user.getUnionid());
            return "forward:/bind-mail.jsp";
        }
        return method(user.getEmail(),user.getPassword());
    }

    //微信绑定邮箱
    @RequestMapping(value = "/bindmail", name = "用户登出")
    public String bindmail(User user) {
        if (user == null) {
            return "forward:/login.jsp";
        }
        user = userService.bindMail(user);
        return method(user.getEmail(),user.getPassword());
    }

    //退出
    @RequestMapping(value = "/logout", name = "用户登出")
    public String logout() {
        SecurityUtils.getSubject().logout();   //登出
        return "forward:login.jsp";
    }

    @RequestMapping("/home")
    public String home() {
        return "home/home";
    }

    public String method(String email, String password) {
        //1.创健令牌
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        //2.获取主题315c682616a05c898f72a0ddd614efd9
        Subject subject = SecurityUtils.getSubject();
        //3.开始认证
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            request.setAttribute("error", "用户名或密码不正确");
            return "forward:login.jsp";
        }
        //获取主角
        User user = (User) subject.getPrincipal();
        session.setAttribute("loginUser", user);
        //根据不同的用户展示不同的菜单
        List<Module> moduleList = moduleService.findModuleListByUser(user);
        session.setAttribute("modules", moduleList);
        return "home/main";
    }

}
