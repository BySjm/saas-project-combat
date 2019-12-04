package cn.bysjm.service.company;

import cn.bysjm.domain.company.Company;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    void save(Company company);

    Company toUpdate(String id);

    void update(Company company);

    void delete(String id);

    PageInfo findPage(Integer page, Integer pageSize);
}
