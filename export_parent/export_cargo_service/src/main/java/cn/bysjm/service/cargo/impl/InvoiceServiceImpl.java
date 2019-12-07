package cn.bysjm.service.cargo.impl;

import cn.bysjm.dao.cargo.InvoiceDao;
import cn.bysjm.domain.cargo.Factory;
import cn.bysjm.domain.cargo.Invoice;
import cn.bysjm.domain.cargo.InvoiceExample;
import cn.bysjm.service.cargo.InvoiceService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;

    @Override
    public void save(Invoice invoice) {

    }

    @Override
    public void update(Invoice invoice) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Factory findById(String id) {
        return null;
    }

    @Override
    public PageInfo findPage(InvoiceExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Invoice> invoiceList = invoiceDao.selectByExample(example);
        return new PageInfo(invoiceList,5);
    }

    @Override
    public List<Factory> findAll(InvoiceExample example) {
        return null;
    }
}
