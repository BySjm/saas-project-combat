package cn.bysjm.controller;


import cn.bysjm.domain.system.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpSession session;

    protected User getUser() {
        return (User) session.getAttribute("loginUser");
    }

    protected String getCompanyId() {
        if (getUser() != null){
            return getUser().getCompanyId();
        }
        return null;
    }

    protected String getCompanyName() {
        if (getUser() != null){
            return getUser().getCompanyName();
        }
        return null;
    }

}
