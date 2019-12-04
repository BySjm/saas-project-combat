package cn.bysjm.service.cargo.impl;

import cn.bysjm.dao.cargo.ContractDao;
import cn.bysjm.dao.cargo.ContractProductDao;
import cn.bysjm.dao.cargo.ExtCproductDao;
import cn.bysjm.domain.cargo.Contract;
import cn.bysjm.domain.cargo.ContractProduct;
import cn.bysjm.domain.cargo.ContractProductExample;
import cn.bysjm.domain.cargo.ExtCproduct;
import cn.bysjm.service.cargo.ContractProductService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContractProductServiceImpl implements ContractProductService {

    @Autowired
    private ContractProductDao productDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ExtCproductDao extCproductDao;

    @Override
    public void save(ContractProduct contractProduct) {
        //单价
        Double price = contractProduct.getPrice();
        //数量
        Integer cnumber = contractProduct.getCnumber();
        //小计金额
        double amount = price * cnumber;
        contractProduct.setAmount(amount);
        //获取合同，修改合同内的货物数量和总计金额
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        contract.setProNum(contract.getProNum() + 1);
        contract.setTotalAmount(contract.getTotalAmount() + amount);
        contractDao.updateByPrimaryKeySelective(contract);//更新合同表
        productDao.insertSelective(contractProduct);
    }

    @Override
    public void update(ContractProduct contractProduct) {
        //获取该货物原有的价钱
        Double amount_old = productDao.selectByPrimaryKey(contractProduct.getId()).getAmount();
        //单价
        Double price = contractProduct.getPrice();
        //数量
        Integer cnumber = contractProduct.getCnumber();
        //小计金额
        double amount_new = price * cnumber;
        contractProduct.setAmount(amount_new);
        //获取合同，修改合同内的货物数量和总计金额
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        contract.setTotalAmount(contract.getTotalAmount() - amount_old + amount_new);
        contractDao.updateByPrimaryKeySelective(contract);//更新合同表
        productDao.updateByPrimaryKeySelective(contractProduct);
    }

    @Override
    public void delete(String id) {
        //原货物
        ContractProduct contractProduct = productDao.selectByPrimaryKey(id);
        //删除所带的附件
        List<ExtCproduct> extCproducts = contractProduct.getExtCproducts();//该货物下的附件列表
        //附件总金额
        Double extTotalAmount = 0.0;
        for (ExtCproduct extCproduct : extCproducts) {
            Double amount = extCproduct.getAmount();//每件附件的金额
            extTotalAmount += amount;
            extCproductDao.deleteByContractId(extCproduct.getId());
        }
        //修改合同下附件的数量
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        contract.setExtNum(contract.getExtNum() - extCproducts.size());
        //修改合同下货物的数量
        contract.setProNum(contract.getProNum() - 1);
        //修改合同金额
        Double amount = contractProduct.getAmount();//该货物的原来的金额
        //该货物的附件金额
        contract.setTotalAmount(contract.getTotalAmount() - amount - extTotalAmount);
        contractDao.updateByPrimaryKeySelective(contract);
        //删除货物
        productDao.deleteByPrimaryKey(id);
    }

    @Override
    public ContractProduct findById(String id) {
        return productDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ContractProductExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<ContractProduct> contractProducts = productDao.selectByExample(example);
        return new PageInfo(contractProducts);
    }

    @Override
    public void saveList(List<ContractProduct> list) {
        for (ContractProduct contractProduct : list) {
            this.save(contractProduct);
        }
    }
}
