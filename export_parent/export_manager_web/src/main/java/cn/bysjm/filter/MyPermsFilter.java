package cn.bysjm.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyPermsFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;

        boolean isPermitted = true;
        if (perms != null && perms.length > 0) {
            if (!subject.isPermitted(perms[0])) {
                for (String perm : perms) {
                    if (!subject.isPermitted(perm)) {
                        isPermitted = false;
                    }
                }
            }
        }
        return isPermitted;
    }
}
