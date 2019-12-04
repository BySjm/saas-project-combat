package cn.bysjm.controller.system;

import cn.bysjm.controller.BaseController;
import cn.bysjm.domain.system.Dept;
import cn.bysjm.service.system.DeptService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/list", name = "展示部门列表数据")
    public String findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", defaultValue = "5")Integer pageSize) {
//        List<Dept> list = deptService.findAll();
        if (page == 0)
            page = 1;
        PageInfo pageInfo = deptService.findPage(page,pageSize,getCompanyId());
        request.setAttribute("page", pageInfo);
        return "system/dept/dept-list";
    }

    @RequestMapping(value = "/toAdd", name = "进入增加部门页面")
    public String toAdd() {
        List<Dept> deptList = deptService.findAll(getCompanyId());
        request.setAttribute("deptList",deptList);
        return "system/dept/dept-add";
    }

    @RequestMapping(value = "/edit", name = "保存/更新部门页面")
    public String edit(Dept dept) {
        if (StringUtils.isEmpty(dept.getId())){
            dept.setId(UUID.randomUUID().toString());
            dept.setCompanyId(getCompanyId());
            dept.setCompanyName(getCompanyName());
            deptService.save(dept);
        }else {
            deptService.update(dept);
        }
        return "redirect:/system/dept/list.do";
    }

    @RequestMapping(value = "/toUpdate", name = "更新保存部门信息页面")
    public String toUpdate(String id) {
        Dept dept = deptService.toUpdate(id);
        List<Dept> deptList = deptService.findAll(getCompanyId());
        request.setAttribute("deptList",deptList);
        request.setAttribute("dept",dept);
        return "system/dept/dept-update";
    }

    @RequestMapping(value = "/delete", name = "删除部门信息")
    public String delete(String id) {
        deptService.delete(id);
        return "redirect:/system/dept/list.do";
    }
}
