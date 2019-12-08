package cn.bysjm.controller.cargo;

import cn.bysjm.controller.BaseController;
import cn.bysjm.domain.cargo.Shipping;
import cn.bysjm.domain.cargo.ShippingExample;
import cn.bysjm.service.cargo.PackingService;
import cn.bysjm.service.cargo.ShippingService;
import cn.bysjm.utils.DownloadUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.provider.DataSourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/cargo/shipping")
public class ShippingController extends BaseController {

    @Reference
    private ShippingService shippingService;

    @Reference
    private PackingService packingService;

    @Autowired
    private DownloadUtil downloadUtil;

    @RequestMapping(value = "/list", name = "查询本企业所有委托单列表")
    public String findPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {

        ShippingExample shippingExample = new ShippingExample();
        shippingExample.createCriteria().andCompanyIdEqualTo(getCompanyId());
        shippingExample.setOrderByClause("create_time desc");
        PageInfo pageInfo = shippingService.findAll(shippingExample, page, size);
        // 将查询信息
        request.setAttribute("page", pageInfo);
        // 转发 逻辑视图
        return "/cargo/shipping/shipping-list";
    }

    @RequestMapping(value = "/toAdd", name = "进入到新增委托单页面")
    public String toAdd(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        // 查询所有的装箱单 根据 状态为1 + 企业di , 并设置到request域中
        Integer state = 1;
        PageInfo pageInfo = packingService.findByState(page, pageSize, state, getCompanyId());
        request.setAttribute("page", pageInfo);

        // 转发 逻辑视图
        return "/cargo/shipping/shipping-add";
    }

    @RequestMapping(value = "/toUpdate", name = "进入到修改委托单页面")
    public String toUpdate(String id, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        // 查询所有的装箱单 根据 状态为1 + 企业di , 并设置到request域中
        Integer state = 1;
        PageInfo pageInfo = packingService.findByState(page, pageSize, state, getCompanyId());
        request.setAttribute("page", pageInfo);

        // 查询要修改的委托单
        Shipping shipping = shippingService.findById(id);
        request.setAttribute("shipping", shipping);
        // 转发 逻辑视图
        return "/cargo/shipping/shipping-update";
    }

    @RequestMapping(value = "/edit", name = "新增或修改委托单")
    public String edit(Shipping shipping, String packingListId) {
        // 新增 修改公用一个方法 根据有无 shippingOrderId判断 具体操作
        if (StringUtils.isEmpty(shipping.getShippingOrderId())) {
            // 为空 新增
            shipping.setShippingOrderId(packingListId); // 设置主键
            shipping.setCompanyId(getCompanyId());
            shipping.setCompanyName(getCompanyName());
            shipping.setCreateBy(getUser().getId());
            shipping.setCreateName(getUser().getUserName());
            shipping.setCreateDept(getUser().getDeptId());
            shipping.setCreateTime(new Date());
            shipping.setState(0); // 设置状态为草稿
            // 新增操作
            shippingService.save(shipping);
        } else {
            // 非空 修改
            shippingService.update(shipping);
        }
        // 重定向 到查询列表
        return "redirect:/cargo/shipping/list.do";
    }

    @RequestMapping(value = "/delete", name = "删除委托单")
    public String delete(String id) {
        shippingService.delete(id);
        // 重定向 到查询列表
        return "redirect:/cargo/shipping/list.do";
    }

    @RequestMapping(value = "/submit", name = "提交委托单")
    public String submit(String id) {
        // 创建对象 根据主键修改状态
        Shipping shipping = new Shipping();
        // 设置属性 主键 和 状态
        shipping.setShippingOrderId(id);
        shipping.setState(1); // 提交 更新状态 0 -> 1已上报
        shippingService.update(shipping);
        // 重定向 到查询列表
        return "redirect:/cargo/shipping/list.do";
    }

    @RequestMapping(value = "/toView", name = "查看委托单")
    public String toView(String id) {
        // 查询对应的委托单对象 设置到request域中
        Shipping shipping = shippingService.findById(id);
        request.setAttribute("shipping", shipping);
        // 转发逻辑视图
        return "/cargo/shipping/shipping-view";
    }

    @RequestMapping(value = "/cancel", name = "取消委托单")
    public String cancel(String id) {
        // 设置委托单状态为草稿
        Shipping shipping = new Shipping();
        shipping.setShippingOrderId(id);
        shipping.setState(0); // 设置状态 1 -> 0:草稿
        shippingService.update(shipping);

        // 重定向 到查询列表
        return "redirect:/cargo/shipping/list.do";
    }

    @RequestMapping(value = "/shippingPdf", name = "导出委托单")
    public void shippingPdf(String id) throws Exception {
        // 查询委托单
        Shipping shipping = shippingService.findById(id);

        List<Shipping> list = new ArrayList<>();
        list.add(shipping);
        // 获取pdf模板 数据流
        String shippingPdfPath = session.getServletContext().getRealPath("/make/PDFTemplate/export_shipping.jasper");
        FileInputStream fileInputStream = new FileInputStream(new File(shippingPdfPath));
        // 数据源
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        // 填充模板数据
        JasperPrint jasperPrint = JasperFillManager.fillReport(fileInputStream, new HashMap<>(), dataSource);

        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

        ByteArrayOutputStream byteArrayOutPutStream = new ByteArrayOutputStream();
        // 将pdf字节数组写入到缓存流中
        byteArrayOutPutStream.write(bytes);
        downloadUtil.download(byteArrayOutPutStream, response, "委托单.pdf");

    }

}
