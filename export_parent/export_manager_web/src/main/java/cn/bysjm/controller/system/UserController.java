package cn.bysjm.controller.system;

import cn.bysjm.controller.BaseController;
import cn.bysjm.domain.system.Dept;
import cn.bysjm.domain.system.Role;
import cn.bysjm.domain.system.User;
import cn.bysjm.service.system.DeptService;
import cn.bysjm.service.system.RoleService;
import cn.bysjm.service.system.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", name = "展示用户列表数据")
    public String findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", defaultValue = "5")Integer pageSize) {
        if (page == 0)
            page = 1;
        PageInfo pageInfo = userService.findPage(page,pageSize,getCompanyId());
        request.setAttribute("page", pageInfo);
        return "system/user/user-list";
    }
    @RequestMapping(value = "/toAdd", name = "进入增加用户页面")
    public String toAdd() {
        List<Dept> userList = deptService.findAll(getCompanyId());
        request.setAttribute("deptList",userList);
        return "system/user/user-add";
    }

    @RequestMapping(value = "/edit", name = "保存/更新用户页面")
    public String edit(User user) {
        if (StringUtils.isEmpty(user.getId())){
            user.setId(UUID.randomUUID().toString());
            user.setCompanyId(getCompanyId());
            user.setCompanyName(getCompanyName());
            userService.save(user);
        }else {
            userService.update(user);
        }
        return "redirect:/system/user/list.do";
    }

    @RequestMapping(value = "/toUpdate", name = "更新保存用户信息页面")
    public String toUpdate(String id) {
        User user = userService.toUpdate(id);
        List<Dept> userList = deptService.findAll(getCompanyId());
        request.setAttribute("deptList",userList);
        request.setAttribute("user",user);
        return "system/user/user-update";
    }

    @RequestMapping(value = "/delete", name = "删除用户信息")
    public String delete(String id) {
        userService.delete(id);
        return "redirect:/system/user/list.do";
    }

    @RequestMapping(value = "roleListroleList",name = "角色权限控制")
    public String roleListroleList(String id){
        User user = userService.toUpdate(id);
        List<String> roleIdList = userService.findRoleIdsByUserId(id);
        //把集合转成字符串
        /*StringBuilder userRoleStr = new StringBuilder();
        for (String s : roleIdList) {
            userRoleStr.append(s);
            userRoleStr.append(",");
        }*/
        //流式编程
        String userRoleStr = roleIdList.stream().collect(Collectors.joining(","));
        List<Role> roleList = roleService.findAll(getCompanyId());
        request.setAttribute("roleList",roleList);
        request.setAttribute("user",user);
        request.setAttribute("userRoleStr",userRoleStr);
        return "system/user/user-role";
    }
    @RequestMapping(value = "/changeRole",name = "改变用户权限")
    public String changeRole(String userid, String[] roleIds){
        userService.changeRole(userid,roleIds);
        return "redirect:/system/user/list.do";
    }
}
