package cn.bysjm.controller.factory;

import cn.bysjm.controller.BaseController;
import cn.bysjm.domain.cargo.ContractProduct;
import cn.bysjm.domain.cargo.ContractProductExample;
import cn.bysjm.domain.cargo.Factory;
import cn.bysjm.domain.cargo.FactoryExample;
import cn.bysjm.service.cargo.ContractProductService;
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
@RequestMapping("/accessory/contractProduct")
public class AccessoryProductController extends BaseController {


    @Reference
    private ContractProductService contractProductService;

    @Reference
    private FactoryService factoryService;



    @Autowired
    private FileUploadUtil fileUploadUtil;

    @RequestMapping(value = "/list",name = "显示货物列表")
    public String list(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10")Integer size){
//        //设置合同ID
//        request.setAttribute("contractId", contractId);

//        货物列表
//        根据合同ID查询货物
//        ContractProductExample example = new ContractProductExample();
//        example.createCriteria().andContractIdEqualTo(contractId);

        String companyId = getCompanyId();

        PageInfo pageInfo = contractProductService.findPage(companyId, page, size);
        request.setAttribute("page", pageInfo);
        return "accessory/contractProduct/product-list";
    }

    @RequestMapping(value = "/toAdd",name = "进入添加页面")
    public String toAdd(){

        List<Factory> factoryList = factoryService.findByState("货物");

        request.setAttribute("factoryList",factoryList);
        return "accessory/contractProduct/product-add";
    }


    @RequestMapping(value = "/edit",name = "保存货物信息")
    public String edit(ContractProduct contractProduct, MultipartFile productPhoto){
        //展示生产厂家
//        FactoryExample exampleFactory = new FactoryExample();
//        exampleFactory.createCriteria().andCtypeEqualTo("货物");
//        List<Factory> factoryList = factoryService.findAll(exampleFactory);
//        request.setAttribute("factoryList", factoryList);

        if (StringUtils.isEmpty(contractProduct.getId())) {
            try {
                String upload = fileUploadUtil.upload(productPhoto);
                contractProduct.setProductImage(upload);//把七牛云返回的url地址存入到货物数据库
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("上传失败");
            }

            contractProduct.setId(UUID.randomUUID().toString());
            contractProduct.setCreateTime(new Date());

//            contractProductService.save(contractProduct);
            contractProduct.setCompanyId(getCompanyId());
            contractProduct.setCompanyName(getCompanyName());



            contractProductService.baoCun(contractProduct);
        } else {
            contractProduct.setUpdateTime(new Date());
            //contractProductService.update(contractProduct);
            contractProductService.xiuGai(contractProduct);
        }
        //重定向到列表页面
        return "redirect:/accessory/contractProduct/list.do?contractId=" + contractProduct.getContractId();
    }

    @RequestMapping(value = "/toUpdate", name = "进入更新页面")
    public String toUpdate(String id) {
        //生产货物的工厂
        FactoryExample exampleFactory = new FactoryExample();
        exampleFactory.createCriteria().andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(exampleFactory);
        request.setAttribute("factoryList", factoryList);
        //货物对象
        ContractProduct contractProduct = contractProductService.findById(id);
        request.setAttribute("contractProduct", contractProduct);
        return "accessory/contractProduct/product-update";
    }
    @RequestMapping(value = "/delete", name = "删除合同页面")
    //id:货物ID，contractId：合同ID
    public String delete(String id, String contractId) {
        //contractProductService.delete(id);
        contractProductService.shanChu(id);
        return "redirect:/accessory/contractProduct/list.do?contractId=" + contractId;
    }







}
