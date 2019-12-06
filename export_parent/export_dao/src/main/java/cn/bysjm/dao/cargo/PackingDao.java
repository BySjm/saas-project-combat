package cn.bysjm.dao.cargo;

import cn.bysjm.domain.cargo.Packing;

import java.util.List;

public interface PackingDao {
    List<Packing> findAll(String companyId);

    void save(Packing packing);

    void update(Packing packing);

    Packing findById(String packingListId);
}
