package cn.zxlee.znotifyapi.service.impl;

import cn.zxlee.znotifyapi.mapper.NoticeMapper;
import cn.zxlee.znotifyapi.pojo.bo.NoticeBO;
import cn.zxlee.znotifyapi.pojo.bo.NoticePageBO;
import cn.zxlee.znotifyapi.pojo.po.NoticePO;
import cn.zxlee.znotifyapi.pojo.vo.NoticeVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.service.INoticeService;
import cn.zxlee.znotifyapi.service.base.impl.BaseInProjectServiceImpl;
import cn.zxlee.znotifyapi.utils.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-25 22:02
 **/

@Service
public class NoticeServiceImpl extends BaseInProjectServiceImpl implements INoticeService<NoticeVO, NoticeBO, NoticePageBO> {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<NoticeVO> list(Map map) {
        checkIsCurrentProject(map.get("token").toString(), map.get("projectId").toString());
        return BeanConvertUtils.convertListTo(noticeMapper.list(map), NoticeVO::new);
    }

    @Override
    public List<NoticeVO> publicList(String projectId) {
        List<NoticePO> poList = noticeMapper.listByExipretime(projectId);
        return BeanConvertUtils.convertListTo(poList, NoticeVO::new);
    }

    @Override
    public PageResultVO<NoticeVO> listByPage(Map map, NoticePageBO pageBO) {
        return baseListByPage(pageBO, map, NoticeVO::new, () -> noticeMapper.list(map));
    }

    @Override
    public int saveOne(String token, NoticeBO noticeBO) {
        checkIsCurrentProject(token, noticeBO.getProjectId());
        return noticeMapper.insertOne(BeanConvertUtils.convertTo(noticeBO, NoticePO::new));
    }

    @Override
    public int updateOne(Map map, String id, NoticeBO bo) {
        super.<NoticePO>checkInProjectForUpdate(noticeMapper, map.get("token").toString(), id);
        return noticeMapper.updateOne(id, BeanConvertUtils.convertTo(bo, NoticePO::new));
    }

    @Override
    public NoticeVO getOneById(String id) {
        return BeanConvertUtils.convertTo(noticeMapper.listById(id), NoticeVO::new);
    }

    @Override
    public int deleteById(Map map, String id) {
        super.<NoticePO>checkInProjectForUpdate(noticeMapper, map.get("token").toString(), id);
        return noticeMapper.deleteById(id);
    }
}
