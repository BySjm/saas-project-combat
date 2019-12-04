package cn.bysjm.service.company.impl;

import cn.bysjm.dao.company.CompanyDao;
import cn.bysjm.domain.company.Company;
import cn.bysjm.service.company.CompanyService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public void save(Company company) {
        companyDao.save(company);
    }

    @Override
    public Company toUpdate(String id) {
        return companyDao.toUpdate(id);
    }

    @Override
    public void update(Company company) {
        companyDao.update(company);
    }

    @Override
    public void delete(String id) {
        companyDao.delete(id);
    }

    @Override
    public PageInfo findPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Company> list = companyDao.findAll();
        return new PageInfo(list,5);
    }
}
