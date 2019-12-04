package cn.bysjm.controller.log;

import cn.bysjm.domain.system.SysLog;
import cn.bysjm.domain.system.User;
import cn.bysjm.service.system.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class AspectLog {

    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;

    @Around("execution(* cn.bysjm.controller.*.*.*(..))")
    public Object saveLog(ProceedingJoinPoint pjp) throws Throwable {

        SysLog sysLog = new SysLog();
        User user = (User) session.getAttribute("loginUser");
        sysLog.setId(UUID.randomUUID().toString());
        sysLog.setUserName(user.getUserName());
        String ip = request.getRemoteAddr();
        sysLog.setIp(ip);
        sysLog.setTime(new Date());
        sysLog.setCompanyId(user.getCompanyId());
        sysLog.setCompanyName(user.getCompanyName());
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();//从方法签名中获取方法对象
        sysLog.setMethod(method.getName());//操作方法名
        if (method.isAnnotationPresent(RequestMapping.class)){
            sysLog.setAction(method.getAnnotation(RequestMapping.class).name());//方法上@RequestMapping注解的name值
        }
        sysLogService.save(sysLog);
        //执行原方法
        return pjp.proceed();
    }

}
