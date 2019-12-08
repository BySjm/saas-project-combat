package cn.bysjm.service.cargo.impl;

import cn.bysjm.dao.cargo.ContractDao;
import cn.bysjm.dao.cargo.ExtCproductDao;
import cn.bysjm.domain.cargo.Contract;
import cn.bysjm.domain.cargo.ExtCproduct;
import cn.bysjm.domain.cargo.ExtCproductExample;
import cn.bysjm.service.cargo.ExtCproductService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ExtCproductServiceImpl implements ExtCproductService {

    @Autowired
    private ExtCproductDao extCproductDao;
    @Autowired
    private ContractDao contractDao;

    @Override
    public void save(ExtCproduct extCproduct) {
        //计算小计金额
        Double amount = extCproduct.getPrice() * extCproduct.getCnumber();
        extCproduct.setAmount(amount);
        //更新附件数量
        String contractId = extCproduct.getContractId();
        Contract contract = contractDao.selectByPrimaryKey(contractId);
        contract.setExtNum(contract.getExtNum() + 1);
        //更新金额
        contract.setTotalAmount(contract.getTotalAmount() + amount);
        contractDao.updateByPrimaryKeySelective(contract);
        extCproductDao.insertSelective(extCproduct);
    }

    @Override
    public void baoCun(ExtCproduct extCproduct) {
        extCproductDao.insertSelective(extCproduct);
    }



    @Override
    public void update(ExtCproduct extCproduct) {
        //旧钱
        Double amount_old = extCproductDao.selectByPrimaryKey(extCproduct.getId()).getAmount();
        //计算小计金额
        Double amount_new = extCproduct.getPrice() * extCproduct.getCnumber();
        extCproduct.setAmount(amount_new);
        //更新附件数量
        String contractId = extCproduct.getContractId();
        Contract contract = contractDao.selectByPrimaryKey(contractId);
        //更新金额
        contract.setTotalAmount(contract.getTotalAmount() - amount_old + amount_new);
        contractDao.updateByPrimaryKeySelective(contract);
        extCproductDao.updateByPrimaryKeySelective(extCproduct);
    }
    @Override
    public void xiuGai(ExtCproduct extCproduct) {
        extCproductDao.updateByPrimaryKeySelective(extCproduct);
    }

    @Override
    public void shanChu(String id) {
        extCproductDao.deleteByPrimaryKey(id);
    }

    @Override
    public void delete(String id) {//附件的ID
        ExtCproduct extCproduct = extCproductDao.selectByPrimaryKey(id);
        //修改合同上附件数量
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        contract.setExtNum(contract.getExtNum() - 1);
        //修改合同上总金额
        contract.setTotalAmount(contract.getTotalAmount() - extCproduct.getAmount());
        contractDao.updateByPrimaryKeySelective(contract);
        extCproductDao.deleteByPrimaryKey(id);
    }

    @Override
    public ExtCproduct findById(String id) {
        return extCproductDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ExtCproductExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<ExtCproduct> extCproducts = extCproductDao.selectByExample(example);
        return new PageInfo(extCproducts);
    }

    @Override
    public PageInfo findPage(String contractProductId, int page, int size) {
        PageHelper.startPage(page,size);
        List<ExtCproduct>extCproducts=extCproductDao.findAll(contractProductId);


        return new PageInfo(extCproducts);
    }


}
