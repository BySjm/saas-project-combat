package cn.bysjm.realm;

import cn.bysjm.domain.system.Module;
import cn.bysjm.domain.system.User;
import cn.bysjm.service.system.ModuleService;
import cn.bysjm.service.system.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SaasRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;

    /**
     * 认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String email = token.getUsername();
        char[] password = token.getPassword();
        String password_page = new String(password);
        User user = userService.findByEmail(email);
        if (user != null){
            String password_db = user.getPassword();
            if (password_db.equals(password_page)){
//                Object principal主角, Object credentials密码, String realmName realm的名字
                return new SimpleAuthenticationInfo(user,password_db,getName());
            }
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //判断当前登录人有哪些权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        List<Module> moduleList = moduleService.findModuleListByUser(user);
        for (Module module : moduleList) {
            info.addStringPermission(module.getName());
        }
        return info;
    }

}
