package cn.bysjm.controller.stat;

import cn.bysjm.controller.BaseController;
import cn.bysjm.service.stat.StatService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stat")
public class StatController extends BaseController {

    @Reference
    private StatService statService;

    @RequestMapping(value = "/toCharts", name = "跳转到统计页面")
    public String toCharts(String chartsType) {
        return "/stat/stat-" + chartsType;
    }

    @RequestMapping(value = "/factoryCharts", name = "跳转到统计页面")
    @ResponseBody
    public List<Map> factoryCharts() {
        return statService.factoryCharts(getCompanyId());
    }
    @RequestMapping(value = "/sellCharts", name = "跳转到统计页面")
    @ResponseBody
    public List<Map> sellCharts() {
        return statService.sellCharts(getCompanyId());
    }
    @RequestMapping(value = "/onlineCharts", name = "跳转到统计页面")
    @ResponseBody
    public List<Map> onlineCharts() {
        return statService.onlineCharts(getCompanyId());
    }

    @RequestMapping(value = "/priceCharts", name = "跳转到货物市场价统计页面")
    @ResponseBody
    public List<Map> priceCharts() {
        return statService.priceCharts(getCompanyId());
    }

    @RequestMapping(value = "/ipCharts", name = "跳转到登录ip统计页面")
    @ResponseBody
    public List<Map> ipCharts() {
        return statService.ipCharts(getCompanyId());
    }
}
