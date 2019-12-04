package cn.bysjm.service.cargo.impl;

import cn.bysjm.dao.cargo.ContractDao;
import cn.bysjm.dao.cargo.ContractProductDao;
import cn.bysjm.dao.cargo.ExportDao;
import cn.bysjm.dao.cargo.ExportProductDao;
import cn.bysjm.domain.Vo.ExportProductResult;
import cn.bysjm.domain.Vo.ExportResult;
import cn.bysjm.domain.cargo.*;
import cn.bysjm.service.cargo.ContractProductService;
import cn.bysjm.service.cargo.ExportProductService;
import cn.bysjm.service.cargo.ExportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ExportServiceImpl implements ExportService {

    @Autowired
    private ExportDao exportDao;

    @Autowired
    private ExportProductDao exportProductDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ContractProductDao contractProductDao;

    @Override
    public Export findById(String id) {
        return exportDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Export export) {

        //保存货物
        //1.查询报运单下的货物
        String contractIds = export.getContractIds();
        ContractProductExample contractProductExample = new ContractProductExample();
        List<String> contractIdList = Arrays.asList(contractIds.split(","));
        contractProductExample.createCriteria().andContractIdIn(contractIdList);//传入一个ContractID的集合
        List<ContractProduct> contractProductList = contractProductDao.selectByExample(contractProductExample);
        //2.根据合同下的货物数据转成报运单下的货物表的数据
        for (ContractProduct contractProduct : contractProductList) {
            ExportProduct exportProduct = new ExportProduct();//报运单下的货物
            BeanUtils.copyProperties(contractProduct, exportProduct);//把合同下的货物->报运单下的货物
            exportProduct.setExportId(export.getId());//保存报运单ID
            List<ExtCproduct> extCproductList = contractProduct.getExtCproducts();//获取货物下的附件
            for (ExtCproduct extCproduct : extCproductList) {
                ExtEproduct extEproduct = new ExtEproduct();//报运单下的附件
                BeanUtils.copyProperties(extCproduct, extEproduct);//把合同下的附件->报运单下的附件
                extEproduct.setExportId(export.getId());//保存报运单ID
                extEproduct.setExportProductId(exportProduct.getId());//保存报运单下货物的ID
            }
        }
        //保存报运单之前还需要 货物总数、附件总数、客户名称使用空格连接
        ContractExample contractExample = new ContractExample();
        contractExample.createCriteria().andIdIn(contractIdList);
        //根据contractIdList查询该合同下的货物的数量和附件的数量
        List<Contract> contractList = contractDao.selectByExample(contractExample);
        Integer totalProNum = 0;//货物的数量
        Integer totalProNumExtNum = 0;//附件的数量
        StringBuffer sb = new StringBuffer();//拼接客户的姓名
        for (Contract contract : contractList) {
            totalProNum += contract.getProNum();
            totalProNumExtNum += contract.getExtNum();
            sb.append(contract.getCustomName()).append(" ");
            contract.setState(2);
            contractDao.updateByPrimaryKeySelective(contract);
        }
        export.setProNum(totalProNum);
        export.setExtNum(totalProNumExtNum);
        export.setCustomerContract(sb.toString());
        //保存报运单
        exportDao.insertSelective(export);
    }

    @Override
    public void update(Export export) {
        //更新报运单
        exportDao.updateByPrimaryKeySelective(export);
        //更新报运单下的货物
        List<ExportProduct> exportProducts = export.getExportProducts();
        for (ExportProduct exportProduct : exportProducts) {
            exportProductDao.updateByPrimaryKeySelective(exportProduct);
        }
    }

    @Override
    public void delete(String id) {
        exportDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ExportExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Export> list = exportDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public void updateE(ExportResult exportResult) {
        //更新两张表   报运单表、货物表
        Export export = new Export();
        export.setId(exportResult.getExportId());
        export.setState(exportResult.getState());
        export.setRemark(exportResult.getRemark());
        exportDao.updateByPrimaryKeySelective(export);
        //货物表
        Set<ExportProductResult> products = exportResult.getProducts();
        ExportProduct exportProduct = null;
        for (ExportProductResult product : products) {
            exportProduct = new ExportProduct();
            exportProduct.setId(product.getExportProductId());
            exportProduct.setTax(product.getTax());
            exportProductDao.updateByPrimaryKeySelective(exportProduct);
        }
    }
}
