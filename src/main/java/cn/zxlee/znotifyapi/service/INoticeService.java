package cn.zxlee.znotifyapi.service;

import cn.zxlee.znotifyapi.pojo.vo.NoticeVO;
import cn.zxlee.znotifyapi.service.base.IBaseService;

import java.util.List;

public interface INoticeService<V, B, PB> extends IBaseService<V, B, PB> {

    List<NoticeVO> publicList(String projectId);

    NoticeVO getOneById(String id);

}
