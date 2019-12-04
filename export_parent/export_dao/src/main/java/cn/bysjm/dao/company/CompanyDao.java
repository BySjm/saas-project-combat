package cn.bysjm.dao.company;

import cn.bysjm.domain.company.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CompanyDao {
    List<Company> findAll();

    void save(Company company);

    Company toUpdate(String id);

    void update(Company company);

    void delete(String id);
}
