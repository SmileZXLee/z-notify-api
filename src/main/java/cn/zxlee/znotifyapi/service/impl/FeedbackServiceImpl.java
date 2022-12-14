package cn.zxlee.znotifyapi.service.impl;

import cn.zxlee.znotifyapi.mapper.FeedbackMapper;
import cn.zxlee.znotifyapi.pojo.bo.FeedbackBO;
import cn.zxlee.znotifyapi.pojo.bo.FeedbackPageBO;
import cn.zxlee.znotifyapi.pojo.po.FeedbackPO;
import cn.zxlee.znotifyapi.pojo.vo.FeedbackVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.service.IFeedbackService;
import cn.zxlee.znotifyapi.service.base.impl.BaseInProjectService;
import cn.zxlee.znotifyapi.utils.BeanConvertUtils;
import cn.zxlee.znotifyapi.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-12-13 19:31
 **/

@Service
public class FeedbackServiceImpl extends BaseInProjectService implements IFeedbackService<FeedbackVO, FeedbackBO, FeedbackPageBO> {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public List<FeedbackVO> list(Map map) {
        return null;
    }

    @Override
    public PageResultVO<FeedbackVO> listByPage(Map map, FeedbackPageBO pageBO) {
        return baseListByPage(pageBO, map, FeedbackVO::new, () -> feedbackMapper.list(map));
    }

    @Override
    public int saveOne(String token, FeedbackBO bo) {
        return 0;
    }

    @Override
    public int updateOne(Map map, String id, FeedbackBO bo) {
        return 0;
    }

    @Override
    public int deleteById(Map map, String id) {
        return 0;
    }

    @Override
    public int publicSaveOne(FeedbackBO bo) {
        checkHasProject(bo.getProjectId());
        return feedbackMapper.insertOne(BeanConvertUtils.convertTo(bo, FeedbackPO::new));
    }
}