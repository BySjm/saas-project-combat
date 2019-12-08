package cn.bysjm.controller.factory;

import cn.bysjm.controller.BaseController;
import cn.bysjm.domain.cargo.ExtCproduct;
import cn.bysjm.domain.cargo.ExtCproductExample;
import cn.bysjm.domain.cargo.Factory;
import cn.bysjm.domain.cargo.FactoryExample;
import cn.bysjm.service.cargo.ContractProductService;
import cn.bysjm.service.cargo.ExtCproductService;
import cn.bysjm.utils.FileUploadUtil;
import cn.itcast.service.factory.FactoryService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/accessory/extCproduct")
public class AccessoryFuJianController extends BaseController {

    @Reference
    private ExtCproductService extCproductService;

    @Reference
    private FactoryService factoryService;

    @Reference
    private ContractProductService contractProductService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @RequestMapping(value = "/list",name = "进入附件列表")
    public String list (@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,String contractProductId){
    request.setAttribute("contractProductId",contractProductId);
//        //展示生产厂家
//        FactoryExample exampleFactory = new FactoryExample();
//        exampleFactory.createCriteria().andCtypeEqualTo("附件");
       // Factory factory = factoryService.findById("4");
        List<Factory> factoryList = factoryService.findByState("附件");

        request.setAttribute("factoryList",factoryList);
        //通过商品id设置附件
//        ExtCproductExample extCproductExample = new ExtCproductExample();
//        extCproductExample.createCriteria().andContractIdEqualTo(contractProductId);
//        PageInfo pageInfo=extCproductService.findAll(extCproductExample,page,size);


//        contractProductId来查询附件的列表
        PageInfo pageInfo1=extCproductService.findPage(contractProductId,page,size);



        request.setAttribute("page",pageInfo1);
        return "accessory/extCproduct/extc-list";

    }
    @RequestMapping(value = "/toAdd",name = "进入添加页面")
    public String toAdd(String contractProductId){
        List<Factory> factoryList = factoryService.findByState("附件");

        request.setAttribute("factoryList",factoryList);
        request.setAttribute("contractProductId",contractProductId);
        return "accessory/extCproduct/extc-add";
    }




    @RequestMapping(value = "/toUpdate", name = "进入更新货物下附件页面")
    public String toUpdate(String id, String contractProductId) {
        //生产附件的工厂
        FactoryExample exampleFactory = new FactoryExample();
        exampleFactory.createCriteria().andCtypeEqualTo("附件");
        List<Factory> factoryList = factoryService.findAll(exampleFactory);
        request.setAttribute("factoryList", factoryList);
        //附件对象
        ExtCproduct extCproduct = extCproductService.findById(id);
        request.setAttribute("extCproduct", extCproduct);
        //设置隐藏域
//        request.setAttribute("contractId",contractId);
        request.setAttribute("contractProductId",contractProductId);
        return "accessory/extCproduct/extc-update";
    }
    @RequestMapping(value = "/edit", name = "保存货物下附件的方法")
    public String edit(String contractProductId,ExtCproduct extCproduct, MultipartFile productPhoto) {
        //文件的保存 七牛云
        try {
            String upload = fileUploadUtil.upload(productPhoto);
            extCproduct.setProductImage(upload); //把七牛云返回的url设置到表中
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(extCproduct.getId())) {
            extCproduct.setId(UUID.randomUUID().toString());
            extCproduct.setCreateTime(new Date());
            request.setAttribute("contractProductId",contractProductId);

            extCproduct.setContractProductId(contractProductId);
            //extCproductService.save(extCproduct);
            extCproductService.baoCun(extCproduct);
        } else {
            extCproduct.setUpdateTime(new Date());
            request.setAttribute("contractProductId",contractProductId);
            //extCproductService.update(extCproduct);
            extCproduct.setContractProductId(contractProductId);
            extCproductService.xiuGai(extCproduct);
        }
        //重定向到列表页面
//        return "redirect:/accessory/extCproduct/list.do?contractProductId=" + extCproduct.getContractId() + "&contractProductId="+extCproduct.getContractProductId();
        return "redirect:/accessory/extCproduct/list.do?contractProductId="+contractProductId;

    }

    @RequestMapping(value = "/delete", name = "删除货物下附件页面")
    //id:货物ID，contractId：合同ID
    public String delete(String id,String contractProductId) {
        //extCproductService.delete(id);
        extCproductService.shanChu(id);
        return "redirect:/accessory/extCproduct/list.do?contractProductId="+contractProductId;

    }





}
