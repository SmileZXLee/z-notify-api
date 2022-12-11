package cn.zxlee.znotifyapi.service.impl;

import cn.zxlee.znotifyapi.exception.CommonException;
import cn.zxlee.znotifyapi.mapper.NoticeMapper;
import cn.zxlee.znotifyapi.mapper.ProjectMapper;
import cn.zxlee.znotifyapi.pojo.bo.NoticeBO;
import cn.zxlee.znotifyapi.pojo.bo.NoticePageBO;
import cn.zxlee.znotifyapi.pojo.po.NoticePO;
import cn.zxlee.znotifyapi.pojo.po.ProjectPO;
import cn.zxlee.znotifyapi.pojo.vo.NoticeVO;
import cn.zxlee.znotifyapi.pojo.vo.ProjectVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.service.INoticeService;
import cn.zxlee.znotifyapi.utils.BeanConvertUtils;
import cn.zxlee.znotifyapi.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-25 22:02
 **/

@Service
public class NoticeServiceImpl implements INoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public List<NoticeVO> list(String token, String projectId) {
        checkIsCurrentProject(token, projectId);
        List<NoticePO> poList = noticeMapper.list(projectId);
        return BeanConvertUtils.convertListTo(poList, NoticeVO::new);
    }

    @Override
    public List<NoticeVO> publicList(String projectId) {
        List<NoticePO> poList = noticeMapper.listByExipretime(projectId);
        return BeanConvertUtils.convertListTo(poList, NoticeVO::new);
    }

    @Override
    public PageResultVO<NoticeVO> listByPage(String token, String projectId, NoticePageBO noticeBO) {
        checkIsCurrentProject(token, projectId);

        PageResultVO<NoticeVO> pageResultVO = new PageResultVO<>();

        PageHelper.startPage(noticeBO.getCurrent(), noticeBO.getPageSize());
        List<NoticePO> noticePOList = noticeMapper.list(projectId);
        PageInfo<NoticePO> pageInfo = new PageInfo<>(noticePOList);
        pageResultVO.setCurrent(pageInfo.getPageNum());
        pageResultVO.setPageSize(pageInfo.getPageSize());
        pageResultVO.setTotal(pageInfo.getTotal());
        pageResultVO.setResults(BeanConvertUtils.convertListTo(pageInfo.getList(), NoticeVO::new));

        PageHelper.clearPage();
        return pageResultVO;
    }

    @Override
    public int saveNotice(String token, NoticeBO noticeBO) {
        checkIsCurrentProject(token, noticeBO.getProjectId());
        NoticePO noticePO = BeanConvertUtils.convertTo(noticeBO, NoticePO::new);
        return noticeMapper.insertNotice(noticePO);
    }

    @Override
    public int updateNotice(String token, String id, NoticeBO noticeBO) {
        NoticePO oldNoticePO = noticeMapper.listById(id);
        if (null == oldNoticePO) {
            throw new CommonException("此通知不存在");
        }
        checkIsCurrentProject(token, oldNoticePO.getProjectId());
        NoticePO noticePO = BeanConvertUtils.convertTo(noticeBO, NoticePO::new);
        return noticeMapper.updateNotice(id, noticePO);
    }

    @Override
    public NoticeVO getOneById(String id) {
        NoticePO noticePO = noticeMapper.listById(id);
        return BeanConvertUtils.convertTo(noticePO, NoticeVO::new);
    }

    @Override
    public int deleteById(String token, String id) {
        NoticePO noticePO = noticeMapper.listById(id);
        if (null == noticePO) {
            throw new CommonException("此通知不存在");
        }
        checkIsCurrentProject(token, noticePO.getProjectId());
        return noticeMapper.deleteById(id);
    }

    /**
     * 查询在当前用户中对应的项目是否存在
     * 必须添加当前用户id的过滤条件，避免绕过用户直接添加通知
     */
    private void checkIsCurrentProject(String token, String projectId) {
        ProjectPO projectPO = projectMapper.listById(tokenUtils.getUserIdByToken(token), projectId);
        if (null == projectPO) {
            throw new CommonException("当前项目不存在");
        }
    }
}
