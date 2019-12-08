package cn.bysjm.controller.system;

import cn.bysjm.controller.BaseController;
import cn.bysjm.domain.system.FeedBack;
import cn.bysjm.domain.system.User;
import cn.bysjm.service.system.FeedBackService;
import cn.bysjm.service.system.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.UUID;
@Controller
@RequestMapping("/system/feedBack")
public class FeedBackController extends BaseController {

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/companyFeeb", name = "进入公司反馈页面")
    public String toFeedBack() {
        request.setAttribute("noun", "公司");
        request.setAttribute("ToPeople",0);
        return "system/feedback/feedBack-commit";
    }

    @RequestMapping(value = "/platformFeed", name = "进入平台反馈页面")
    public String feedPlatform() {

        request.setAttribute("noun", "SaaS平台");
        request.setAttribute("ToPeople",1);

        return "system/feedback/feedBack-commit";
    }

    @RequestMapping(value = "/list", name = "查询所有的反馈")
    public String findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        //根据userId进行查询
        PageInfo<FeedBack> pageInfo = feedBackService.findByPage(getUser().getId(), page, size);
        request.setAttribute("page", pageInfo);
        return "system/feedback/feedBack-list";
    }

    @RequestMapping(value = "/save", name = "保存反馈的方法")
    public String save(FeedBack feedback) {
        User user = getUser();
        //给feedback设置其他属性
        feedback.setId(UUID.randomUUID().toString());
        feedback.setInputBy(user.getUserName());
        feedback.setInputTime(new Date());
        feedback.setTel(user.getTelephone());
        if(feedback.getToPeople() == 1){
            //Saas管理员id
//            List<User> list = userService.findByDegree(0);
//            User SaaSguanliyuan = list.get(new Random().nextInt(list.size()));
//            feedback.setAnswerBy(SaaSguanliyuan.getId());
            feedback.setAnswerBy("9fe270f8-f682-4126-92aa-604d945c95f1");
        }else{
            //企业管理员id
//            List<User> list  = userService.findbyCompanyIdAndDegree(user.getCompanyId(),1);
//            User gunaliyuan = list.get(new Random().nextInt(list.size()));
//            feedback.setAnswerBy(gunaliyuan.getId());
            feedback.setAnswerBy("002108e2-9a10-4510-9683-8d8fd1d374ef");
        }
        feedback.setState(0);
        feedback.setCreateBy(user.getId());
        feedback.setCreateTime(new Date());
        feedback.setCompanyId(getCompanyId());
        feedback.setCompanyName(getCompanyName());

        feedBackService.save(feedback);

        return "redirect:/system/feedBack/list.do";
    }

    @RequestMapping(value = "/findFeedBackNum",name = "获取右上角的反馈数量")
    @ResponseBody
    public String findFeedBackNum(){
        Integer count = feedBackService.findFeedBackNum(getUser());
        return count.toString();
    }


    @RequestMapping(value = "/solve",name = "查询出所有的反馈")
    public String solve(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10")Integer size){
        PageInfo<FeedBack> pageInfo = feedBackService.findFeedBack(getUser(),page,size);
        request.setAttribute("page",pageInfo);
        request.setAttribute("degree",getUser().getDegree());
        return "system/feedback/feedBack-list2";
    }


    @RequestMapping(value = "/toUpdate",name = "跳转到处理反馈的页面")
    public String toUpdate(String id){

        FeedBack feedback = feedBackService.findById(id);

        request.setAttribute("feedback",feedback);

        return "system/feedback/feedBack-update";
    }

    @RequestMapping(value = "/update",name = "更新反馈")
    public String update(FeedBack feedback){
        feedBackService.update(feedback);
        return "redirect:/system/feedBack/solve.do";
    }

}
