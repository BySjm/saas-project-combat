package cn.bysjm.service.stat.impl;

import cn.bysjm.dao.stat.StatDao;
import cn.bysjm.service.stat.StatService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatDao statDao;

    @Override
    public List<Map> factoryCharts(String companyId) {
        return statDao.factoryCharts(companyId);
    }

    @Override
    public List<Map> sellCharts(String companyId) {
        return statDao.sellCharts(companyId);
    }

    @Override
    public List<Map> onlineCharts(String companyId) {
        return statDao.onlineCharts(companyId);
    }
}
