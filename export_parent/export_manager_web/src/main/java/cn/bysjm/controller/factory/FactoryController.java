package cn.bysjm.controller.factory;

import cn.bysjm.controller.BaseController;
import cn.bysjm.domain.cargo.Factory;
import cn.bysjm.domain.cargo.FactoryExample;
import cn.itcast.service.factory.FactoryService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/factory")
public class FactoryController extends BaseController {

    @Reference
    private FactoryService factoryService;

    @RequestMapping(value = "/list",name = "展示生产厂商数据")
    public String list(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10")Integer pageSize){
        //构建生产厂商列表
        //1.创建example
        FactoryExample example = new FactoryExample();
        //2.创建criteria
        FactoryExample.Criteria criteria = example.createCriteria();
        //3.添加查询条件
        criteria.andStateEqualTo("1");


//        PageInfo <Factory>pageInfo = factoryService.findPage(example, page, pageSize);
        PageInfo pageInfo = factoryService.findPage(example, page, pageSize);
        request.setAttribute("page",pageInfo);

        return "factory/factory-list";
    }

    @RequestMapping(value = "/toAdd",name = "进入添加生产工厂的页面")
    public String toAdd(){
        return "factory/factory-add";
    }





    @RequestMapping(value = "/edit",name = "保存生产工厂数据")
    public String edit(Factory factory){
         if (StringUtils.isEmpty(factory.getId())){
             //正确说明已经进入新增的方法中
             //1.设置id
             factory.setId(UUID.randomUUID().toString());
             //2.设置基本属性
             factory.setState(1);
             factory.setCreateTime(new Date());//创建时间
             factory.setUpdateTime(new Date());
             factoryService.save(factory);
         }else {

             factory.setUpdateTime(new Date());//创建时间
             factoryService.update(factory);
         }
         return "redirect:/factory/list.do";
    }

    @RequestMapping(value = "/toUpdate",name = "进入修改生产工厂的页面")
    public String toUpdate(String id){
        Factory factory=factoryService.findById(id);
        request.setAttribute("factory",factory);
        return "factory/factory-update";
    }

    @RequestMapping(value = "/delete",name = "删除生产工厂的信息")
    public String delete(String id){
        /*//1.创建example
        FactoryExample example = new FactoryExample();
        //2.创建criteria
        FactoryExample.Criteria criteria = example.createCriteria();
        //3.添加查询条件
        FactoryExample.Criteria criteria1 = criteria.andStateEqualTo("1");*/
        //factory.setState(0);


        factoryService.delete(id);
        return "redirect:/factory/list.do";
    }




}
