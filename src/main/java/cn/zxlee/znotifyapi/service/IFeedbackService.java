package cn.zxlee.znotifyapi.service;

import cn.zxlee.znotifyapi.pojo.bo.FeedbackBO;
import cn.zxlee.znotifyapi.pojo.bo.FeedbackReplyBO;
import cn.zxlee.znotifyapi.pojo.vo.FeedbackVO;
import cn.zxlee.znotifyapi.service.base.IBaseService;

import java.util.List;


public interface IFeedbackService<V, B, PB> extends IBaseService<V, B, PB> {
    int publicSaveOne(FeedbackBO bo);
    int updateReply(String token, String id, FeedbackReplyBO bo);
    List<FeedbackVO> publicListByUsername(String projectId, String username);
}
