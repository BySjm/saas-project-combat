package cn.bysjm.service.cargo.impl;

import cn.bysjm.dao.cargo.ExportDao;
import cn.bysjm.dao.cargo.PackingDao;
import cn.bysjm.dao.cargo.ShippingDao;
import cn.bysjm.domain.cargo.Export;
import cn.bysjm.domain.cargo.Packing;
import cn.bysjm.domain.cargo.Shipping;
import cn.bysjm.domain.cargo.ShippingExample;
import cn.bysjm.service.cargo.ShippingService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingDao shippingDao;

    @Autowired
    private PackingDao packingDao;

    @Autowired
    private ExportDao exportDao;

    @Override
    public Shipping findById(String shippingOrderId) {
        return shippingDao.selectByPrimaryKey(shippingOrderId);
    }

    @Override
    public void save(Shipping shipping) {
        // 先根据装箱单id查询 装箱单 (委托单的id等于 装箱单的id)
        // 根据装箱单的exportId报运单id查询报运单的数据
        // 将报运单中的数据设置到委托单中
        // 最后将装箱单的状态设置为 2 委托
        // 查询装箱单
        String packingListId = shipping.getShippingOrderId();
        Packing packing = packingDao.findById(packingListId);

        // 修改装箱单的状态 1 - 2委托
        packing.setState(2);
        packingDao.update(packing);

        // 查询报运单
        String exportId = packing.getExportId();
        Export export = exportDao.selectByPrimaryKey(exportId);

        // 修改报运单的状态 3 - 4委托
        export.setState(4);
        exportDao.updateByPrimaryKeySelective(export);

        // 从报运单中拷贝需要的数据到委托单
        shipping.setOrderType(export.getTransportMode()); // 运输方式
        shipping.setLcNo(export.getLcno()); // 信用证号
        shipping.setPortOfLoading(export.getShipmentPort()); // 装货港
        shipping.setPortOfDischarge(export.getDestinationPort()); // 卸货港目的港

        shippingDao.insertSelective(shipping);
    }

    @Override
    public void update(Shipping shipping) {
        shippingDao.updateByPrimaryKeySelective(shipping);
    }

    @Override
    public void delete(String shippingOrderId) {
        shippingDao.deleteByPrimaryKey(shippingOrderId);
    }

    @Override
    public PageInfo findAll(ShippingExample shippingExample, int page, int size) {
        PageHelper.startPage(page, size);
        List<Shipping> shippings = shippingDao.selectByExample(shippingExample);
        return new PageInfo(shippings, 5);
    }


}
