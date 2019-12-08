package cn.bysjm.service.system.impl;

import cn.bysjm.dao.system.FeedBackDao;
import cn.bysjm.domain.system.FeedBack;
import cn.bysjm.domain.system.User;
import cn.bysjm.service.system.FeedBackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackServiceImpl implements FeedBackService {
    @Autowired
    private FeedBackDao feedBackDao;
    @Override
    public PageInfo<FeedBack> findByPage(String id, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<FeedBack> list = feedBackDao.findAll(id);
        return new PageInfo(list,5);
    }

    @Override
    public void save(FeedBack feedback) {
        feedBackDao.save(feedback);
    }

    @Override
    public Integer findFeedBackNum(User user) {
        return feedBackDao.findByState();
    }

    @Override
    public PageInfo<FeedBack> findFeedBack(User user, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<FeedBack> list= feedBackDao.findFeedBack(user.getId());
        return new PageInfo(list,5);
    }

    @Override
    public FeedBack findById(String id) {
        return feedBackDao.findById(id);
    }

    @Override
    public void update(FeedBack feedback) {
         feedBackDao.update(feedback);
    }
}
