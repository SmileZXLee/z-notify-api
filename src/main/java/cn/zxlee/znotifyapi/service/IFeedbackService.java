package cn.zxlee.znotifyapi.service;

import cn.zxlee.znotifyapi.pojo.bo.FeedbackBO;
import cn.zxlee.znotifyapi.service.base.IBaseService;


public interface IFeedbackService<V, B, PB> extends IBaseService<V, B, PB> {
    int publicSaveOne(FeedbackBO bo);
}
