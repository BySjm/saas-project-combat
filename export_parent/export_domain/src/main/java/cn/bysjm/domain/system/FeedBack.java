package cn.bysjm.domain.system;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class FeedBack {
    private String Id;
    private String InputBy;//提出人
    private Date InputTime;//提出时间
    private String Title;//标题
    private String Content;//内容
    private Integer ClassType;// '1管理2安全3建议4其他',
    private String Tel;//电话
    private String AnswerBy;//解决人
    private String AnswerUserName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date AnswerTime;//解决时间
    private String SolveMethod;//解决办法
    private Character Resolution; //1已修改2无需修改3重复问题4描述不完整5无法再现6其他
    private Character Difficulty; //1已修改2无需修改3重复问题4描述不完整5无法再现6其他
    private Character IsShare;  //0不公开1公开
    private Integer State;  //0未处理1已处理
    private String CreateBy;
    private String CreateDept;
    private Date CreateTime;
    private String CompanyId;
    private String CompanyName;
    private Integer ToPeople;

}
