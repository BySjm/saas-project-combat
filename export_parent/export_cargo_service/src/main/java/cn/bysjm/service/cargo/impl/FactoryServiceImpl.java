package cn.bysjm.service.cargo.impl;

import cn.bysjm.dao.cargo.FactoryDao;
import cn.bysjm.domain.cargo.Factory;
import cn.bysjm.domain.cargo.FactoryExample;
import cn.bysjm.service.cargo.FactoryService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryDao factoryDao;

    @Override
    public void save(Factory factory) {
        factoryDao.insertSelective(factory);
    }

    @Override
    public void update(Factory factory) {
    factoryDao.updateByPrimaryKeySelective(factory);
    }

    @Override
    public void delete(String id) {
factoryDao.deleteByPrimaryKey(id);
    }

    @Override
    public Factory findById(String id) {
        return factoryDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo findPage(FactoryExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Factory> list = factoryDao.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public List<Factory> findAll(FactoryExample example) {
        return factoryDao.selectByExample(example);
    }

//    @Override
//    public PageInfo<Factory> findPage(FactoryExample example, Integer page, Integer pageSize) {
//        PageHelper.startPage(page,pageSize);//使用分页插件
//
//        List<Factory>list=factoryDao.selectByExample(example);
//
//        return new PageInfo<Factory>(list,10);
//    }


}
