package cn.bysjm.controller.system;

import cn.bysjm.controller.BaseController;
import cn.bysjm.domain.system.Module;
import cn.bysjm.service.system.ModuleService;
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
@RequestMapping("/system/module")
public class ModuleController extends BaseController {

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "/list", name = "展示模块列表数据")
    public String findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", defaultValue = "10")Integer pageSize) {
        if (page == 0)
            page = 1;
        PageInfo pageInfo = moduleService.findPage(page,pageSize);
        request.setAttribute("page", pageInfo);
        return "system/module/module-list";
    }

    @RequestMapping(value = "/toAdd", name = "进入增加模块页面")
    public String toAdd() {
        List<Module> menus = moduleService.findAll();
        request.setAttribute("menus",menus);
        return "system/module/module-add";
    }

    @RequestMapping(value = "/edit", name = "保存/更新模块页面")
    public String edit(Module module) {
        if (StringUtils.isEmpty(module.getId())){
            module.setId(UUID.randomUUID().toString());
            moduleService.save(module);
        }else {
            moduleService.update(module);
        }
        return "redirect:/system/module/list.do";
    }

    @RequestMapping(value = "/toUpdate", name = "更新保存模块信息页面")
    public String toUpdate(String id) {
        Module module = moduleService.toUpdate(id);
        request.setAttribute("module",module);
        List<Module> menus = moduleService.findAll();
        request.setAttribute("menus",menus);
        return "system/module/module-update";
    }

    @RequestMapping(value = "/delete", name = "删除模块信息")
    public String delete(String id) {
        moduleService.delete(id);
        return "redirect:/system/module/list.do";
    }
}
