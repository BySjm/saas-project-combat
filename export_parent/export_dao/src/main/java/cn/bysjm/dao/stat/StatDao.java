package cn.bysjm.dao.stat;

import java.util.List;
import java.util.Map;

public interface StatDao {
    List<Map> factoryCharts(String companyId);

    List<Map> sellCharts(String companyId);

    List<Map> onlineCharts(String companyId);

    List<Map> priceCharts(String companyId);

    List<Map> ipCharts(String companyId);
}
