package cn.zxlee.znotifyapi.service;

import cn.zxlee.znotifyapi.pojo.bo.NoticeBO;
import cn.zxlee.znotifyapi.pojo.bo.NoticePageBO;
import cn.zxlee.znotifyapi.pojo.bo.ProjectPageBO;
import cn.zxlee.znotifyapi.pojo.vo.NoticeVO;
import cn.zxlee.znotifyapi.pojo.vo.ProjectVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;

import java.util.List;

public interface INoticeService {

    List<NoticeVO> list(String token, String projectId);

    List<NoticeVO> publicList(String projectId);

    PageResultVO<NoticeVO> listByPage(String token, String projectId, NoticePageBO noticeBO);

    int saveNotice(String token, NoticeBO noticeBO);

    int updateNotice(String token, String id, NoticeBO noticeBO);

    NoticeVO getOneById(String id);

    int deleteById(String token, String id);
}
