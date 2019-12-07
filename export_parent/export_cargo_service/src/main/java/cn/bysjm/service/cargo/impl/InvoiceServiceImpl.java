package cn.bysjm.service.cargo.impl;

import cn.bysjm.dao.cargo.ExportDao;
import cn.bysjm.dao.cargo.InvoiceDao;
import cn.bysjm.dao.cargo.PackingDao;
import cn.bysjm.dao.cargo.ShippingDao;
import cn.bysjm.domain.cargo.*;
import cn.bysjm.service.cargo.ExportService;
import cn.bysjm.service.cargo.InvoiceService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private ShippingDao shippingDao;

    @Autowired
    private PackingDao packingDao;

    @Autowired
    private ExportDao exportDao;

    @Override
    public void save(Invoice invoice) {
        //修改发票
        invoiceDao.insert(invoice);
        //修改委托单的状态
        Shipping shipping = new Shipping();
        shipping.setShippingOrderId(invoice.getInvoiceId());
        shipping.setState(2);
        shippingDao.updateByPrimaryKeySelective(shipping);
        //修改装箱单页面
        Packing packing = new Packing();
        packing.setPackingListId(invoice.getInvoiceId());
        packing.setState(3);
        packingDao.update(packing);
        //修改报运单状态
        Export export = new Export();
        export.setState(5);//xxx->发票
        export.setId(packingDao.findById(invoice.getInvoiceId()).getExportId());
        exportDao.updateByPrimaryKeySelective(export);
    }

    @Override
    public void update(Invoice invoice) {
        invoiceDao.updateByPrimaryKeySelective(invoice);
    }

    @Override
    public void delete(String id) {
        invoiceDao.deleteByPrimaryKey(id);
        //修改委托单的状态
        Shipping shipping = new Shipping();
        shipping.setShippingOrderId(id);
        shipping.setState(1);
        shippingDao.updateByPrimaryKeySelective(shipping);
        //修改装箱单状态
        Packing packing = new Packing();
        packing.setPackingListId(id);
        packing.setState(2);
        packingDao.update(packing);
        //修改报运单状态
        Export export = new Export();
        export.setState(4);//xxx->委托，回退到上一步
        export.setId(packingDao.findById(id).getExportId());
        exportDao.updateByPrimaryKeySelective(export);
    }

    @Override
    public Invoice findById(String id) {
        return invoiceDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findPage(InvoiceExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Invoice> invoiceList = invoiceDao.selectByExample(example);
        return new PageInfo(invoiceList,5);
    }

    @Override
    public List<Invoice> findAll(InvoiceExample example) {
        return invoiceDao.selectByExample(example);
    }

}
