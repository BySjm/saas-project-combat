package cn.bysjm.controller.company;

import cn.bysjm.controller.BaseController;
import cn.bysjm.domain.company.Company;
import cn.bysjm.service.company.CompanyService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

    @Reference
    private CompanyService companyService;

    @RequestMapping(value = "/list", name = "展示企业列表数据")
    @RequiresPermissions("企业管理")
    public String findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", defaultValue = "2")Integer pageSize) {
//        List<Company> list = companyService.findAll();
        if (page == 0)
            page = 1;
        PageInfo pageInfo = companyService.findPage(page,pageSize);
        request.setAttribute("page", pageInfo);
        return "company/company-list";
    }

    @RequestMapping(value = "/toAdd", name = "进入增加企业页面")
    public String toAdd(Model model) {
        return "company/company-add";
    }

    @RequestMapping(value = "/edit", name = "保存/更新企业页面")
    public String edit(Company company) {
        if (StringUtils.isEmpty(company.getId())){
            company.setId(UUID.randomUUID().toString());
            companyService.save(company);
        }else {
            companyService.update(company);
        }

        return "redirect:/company/list.do";
    }

    @RequestMapping(value = "/toUpdate", name = "更新保存企业信息页面")
    public String toUpdate(String id) {
        Company company = companyService.toUpdate(id);
        request.setAttribute("company",company);
        return "company/company-update";
    }

    @RequestMapping(value = "/delete", name = "删除企业信息")
    public String delete(String id) {
        companyService.delete(id);
        return "redirect:/company/list.do";
    }
}
