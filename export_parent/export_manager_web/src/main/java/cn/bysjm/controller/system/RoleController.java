package cn.bysjm.controller.system;

import cn.bysjm.controller.BaseController;
import cn.bysjm.domain.system.Module;
import cn.bysjm.domain.system.Role;
import cn.bysjm.service.system.ModuleService;
import cn.bysjm.service.system.RoleService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "/list", name = "展示角色列表数据")
    public String findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        if (page == 0)
            page = 1;
        PageInfo pageInfo = roleService.findPage(page, pageSize, getCompanyId());
        request.setAttribute("page", pageInfo);
        return "system/role/role-list";
    }

    @RequestMapping(value = "/toAdd", name = "进入增加角色页面")
    public String toAdd() {
        List<Role> roleList = roleService.findAll(getCompanyId());
        request.setAttribute("roleList", roleList);
        return "system/role/role-add";
    }

    @RequestMapping(value = "/edit", name = "保存/更新角色页面")
    public String edit(Role role) {
        if (StringUtils.isEmpty(role.getId())) {
            role.setId(UUID.randomUUID().toString());
            role.setCompanyId(getCompanyId());
            role.setCompanyName(getCompanyName());
            roleService.save(role);
        } else {
            roleService.update(role);
        }
        return "redirect:/system/role/list.do";
    }

    @RequestMapping(value = "/toUpdate", name = "更新保存角色信息页面")
    public String toUpdate(String id) {
        Role role = roleService.toUpdate(id);
        List<Role> roleList = roleService.findAll(getCompanyId());
        request.setAttribute("roleList", roleList);
        request.setAttribute("role", role);
        return "system/role/role-update";
    }

    @RequestMapping(value = "/delete", name = "删除角色信息")
    public String delete(String id) {
        roleService.delete(id);
        return "redirect:/system/role/list.do";
    }

    @RequestMapping(value = "roleModule", name = "构造模块数据")
    public String roleModule(String roleid) {
        Role role = roleService.findRoleIdsByRoleId(roleid);
        request.setAttribute("role", role);
        return "system/role/role-module";
    }

    @RequestMapping(value = "getZtreeNodes", name = "获取树节点")
    @ResponseBody
    public List<Map<String,String>> getZtreeNodes(String roleid) {
        //根据roleid查询需要回显的数据
        List<String> moduleIdList = moduleService.findModulesByRoleId(roleid);
        //查询所有的菜单，并封装返回
        List<Module> moduleList = moduleService.findAll();
        Map<String,String> ztreeNode = null;
        List<Map<String,String>> ztreeNodes = new ArrayList<>();
        for (Module module : moduleList) {
            ztreeNode = new HashMap<>();
            ztreeNode.put("id",module.getId());
            ztreeNode.put("pId",module.getParentId());
            ztreeNode.put("name",module.getName());
            if (moduleIdList.contains(module.getId())){
                ztreeNode.put("checked","true");
            }
            ztreeNodes.add(ztreeNode);
        }
        int a = 2+3;
        return ztreeNodes;
    }

    @RequestMapping(value = "updateRoleModule", name = "")
    public String updateRoleModule(String roleid, String moduleIds) {
        roleService.updateRoleModule(roleid,moduleIds);
        return "redirect:/system/role/list.do";
    }
}
