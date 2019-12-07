package cn.bysjm.controller.cargo;

import cn.bysjm.controller.BaseController;
import cn.bysjm.domain.cargo.Export;
import cn.bysjm.domain.cargo.Invoice;
import cn.bysjm.domain.cargo.InvoiceExample;
import cn.bysjm.domain.cargo.Packing;
import cn.bysjm.service.cargo.InvoiceService;
import cn.bysjm.service.cargo.PackingService;
import cn.bysjm.service.cargo.ShippingService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

//装箱
@Controller
@RequestMapping("/cargo/invoice")
public class InvoiceController extends BaseController {

    @Reference
    private InvoiceService invoiceService;

    @Reference
    private ShippingService shippingService;

    @Reference
    private PackingService packingService;

    @RequestMapping(value = "/list", name = "发票管理")
    public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        InvoiceExample invoiceExample = new InvoiceExample();
        invoiceExample.createCriteria().andCompanyIdEqualTo(getCompanyId());
        PageInfo pageInfo = invoiceService.findPage(invoiceExample, page, size);
        request.setAttribute("page",pageInfo);
        return "cargo/invoice/invoice-list";
    }

    @RequestMapping(value = "/toAdd",name = "进入新增发票页面")
    public String toAdd(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        PageInfo pageInfo = shippingService.findByState(page, size, 1, getCompanyId());
        request.setAttribute("page",pageInfo);
        return "cargo/invoice/invoice-add";
    }

    @RequestMapping(value = "/edit",name = "新增/更新发票单")
    public String edit(Invoice invoice, String packingListId) {
        if (StringUtils.isEmpty(invoice.getInvoiceId())) {
            invoice.setInvoiceId(packingListId);
            invoice.setScNo(packingListId);//合同号->packingListId
            invoice.setState(0);//草稿
            invoice.setCreateBy(getUser().getId());
            invoice.setCreateDept(getUser().getDeptId());
            invoice.setCreateTime(new Date());
            invoice.setCompanyId(getCompanyId());
            invoice.setCompanyName(getCompanyName());
            invoiceService.save(invoice);
        } else {
            invoiceService.update(invoice);
        }
        return "redirect:/cargo/invoice/list.do";
    }

    @RequestMapping(value = "/toUpdate",name = "新增/更新发票")
    public String edit(String id) {
        Invoice invoice = invoiceService.findById(id);
        request.setAttribute("invoice",invoice);
        return "cargo/invoice/invoice-update";
    }

    @RequestMapping(value = "/submit",name = "提交发票")
    public String submit(String invoiceId) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceId);
        invoice.setState(1);
        invoiceService.update(invoice);
        return "redirect:/cargo/invoice/list.do";
    }

    @RequestMapping(value = "/cancel",name = "取消发票")
    public String cancel(String invoiceId) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceId);
        invoice.setState(0);//改为草稿状态
        invoiceService.update(invoice);
        return "redirect:/cargo/invoice/list.do";
    }

    @RequestMapping(value = "/delete",name = "删除发票")
    public String delete(String invoiceId) {
        invoiceService.delete(invoiceId);
        return "redirect:/cargo/invoice/list.do";
    }

    @RequestMapping(value = "/toView",name = "查看发票")
    public String toView(String invoiceId) {
        Invoice invoice = invoiceService.findById(invoiceId);
        request.setAttribute("invoice",invoice);
        return "cargo/invoice/invoice-view";
    }
}
