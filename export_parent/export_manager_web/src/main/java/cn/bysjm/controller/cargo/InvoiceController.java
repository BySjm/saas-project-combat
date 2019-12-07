package cn.bysjm.controller.cargo;

import cn.bysjm.controller.BaseController;
import cn.bysjm.domain.cargo.InvoiceExample;
import cn.bysjm.service.cargo.InvoiceService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//装箱
@Controller
@RequestMapping("/cargo/invoice")
public class InvoiceController extends BaseController {

    @Reference
    private InvoiceService invoiceService;

    @RequestMapping(value = "/list", name = "装箱管理")
    public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        InvoiceExample invoiceExample = new InvoiceExample();
        invoiceExample.createCriteria().andCompanyIdEqualTo(getCompanyId());
        PageInfo pageInfo = invoiceService.findPage(invoiceExample, page, size);
        request.setAttribute("page",pageInfo);
        return "cargo/invoice/invoice-list";
    }

}
