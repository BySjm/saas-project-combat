package cn.bysjm.service.cargo.impl;

import cn.bysjm.dao.cargo.ContractDao;
import cn.bysjm.dao.cargo.ContractProductDao;
import cn.bysjm.dao.cargo.ExtCproductDao;
import cn.bysjm.domain.Vo.ContractProductVo;
import cn.bysjm.domain.cargo.Contract;
import cn.bysjm.domain.cargo.ContractExample;
import cn.bysjm.service.cargo.ContractService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ContractProductDao contractProductDao;
    @Autowired
    private ExtCproductDao extCproductDao;


    @Override
    public Contract findById(String id) {
        return contractDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Contract contract) {
        contractDao.insertSelective(contract);
    }

    @Override
    public void update(Contract contract) {
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void delete(String id) {
        //附件删除
        extCproductDao.deleteByContractId(id);
        //货物删除
        contractProductDao.deleteByContractId(id);
        contractDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo findAll(ContractExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Contract> contracts = contractDao.selectByExample(example);
        return new PageInfo(contracts,5);
    }

    @Override
    public List<ContractProductVo> findContractProductVoByShipTime(String inputDate,String companyId) {
        return contractDao.findContractProductVoByShipTime(inputDate,companyId);
    }
}
