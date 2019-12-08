package cn.bysjm.dao.system;

import cn.bysjm.domain.system.FeedBack;
import cn.bysjm.domain.system.User;

import java.util.List;

public interface FeedBackDao {
    List<FeedBack> findAll(String id);

    void save(FeedBack feedback);

    FeedBack findById(String id);

    void update(FeedBack feedback);

    Integer findByState();

    List<FeedBack> findFeedBack(String answerby);

}
