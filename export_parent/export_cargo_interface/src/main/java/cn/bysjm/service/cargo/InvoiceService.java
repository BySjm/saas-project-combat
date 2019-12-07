package cn.bysjm.service.cargo;

import cn.bysjm.domain.cargo.Factory;
import cn.bysjm.domain.cargo.FactoryExample;
import cn.bysjm.domain.cargo.Invoice;
import cn.bysjm.domain.cargo.InvoiceExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface InvoiceService {

    /**
     * 保存
     */
    void save(Invoice invoice);

    /**
     * 更新
     */
    void update(Invoice invoice);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 根据id查询
     */
    Factory findById(String id);

    //分页查询
    PageInfo findPage(InvoiceExample example, int page, int size);
    //查询所有
    public List<Factory> findAll(InvoiceExample example);

}
