package cn.bysjm.service.cargo.impl;

import cn.bysjm.dao.cargo.PackingDao;
import cn.bysjm.domain.cargo.Packing;
import cn.bysjm.service.cargo.PackingService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PackingServiceImpl implements PackingService {

    @Autowired
    private PackingDao packingDao;

    @Override
    public PageInfo findAll(Integer page, Integer pageSize, String companyId) {
        PageHelper.startPage(page,pageSize);
        List<Packing> packingList = packingDao.findAll(companyId);
        return new PageInfo<>(packingList,5);
    }

    @Override
    public void save(Packing packing) {
        packingDao.save(packing);
    }

    @Override
    public void update(Packing packing) {
        packingDao.update(packing);
    }

    @Override
    public Packing findById(String packingListId) {
        return packingDao.findById(packingListId);
    }

    @Override
    public PageInfo findByState(Integer page, Integer pageSize, Integer state, String companyId) {
        PageHelper.startPage(page,pageSize);
        List<Packing> packingList = packingDao.findByState(state, companyId);
        return new PageInfo<>(packingList,5);
    }
}
