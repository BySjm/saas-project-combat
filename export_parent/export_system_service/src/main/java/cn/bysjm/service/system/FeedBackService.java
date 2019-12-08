package cn.bysjm.service.system;

import cn.bysjm.domain.system.FeedBack;
import cn.bysjm.domain.system.User;
import com.github.pagehelper.PageInfo;

public interface FeedBackService {

    PageInfo<FeedBack> findByPage(String id, Integer page, Integer size);

    void save(FeedBack feedback);

    Integer findFeedBackNum(User user);

    PageInfo<FeedBack> findFeedBack(User user, Integer page, Integer size);

    FeedBack findById(String id);

    void update(FeedBack feedback);
}
