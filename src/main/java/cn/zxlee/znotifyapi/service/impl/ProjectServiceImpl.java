package cn.zxlee.znotifyapi.service.impl;

import cn.zxlee.znotifyapi.exception.CommonException;
import cn.zxlee.znotifyapi.mapper.ProjectMapper;
import cn.zxlee.znotifyapi.pojo.bo.ProjectBO;
import cn.zxlee.znotifyapi.pojo.bo.ProjectPageBO;
import cn.zxlee.znotifyapi.pojo.po.ProjectPO;
import cn.zxlee.znotifyapi.pojo.vo.ProjectVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.service.IProjectService;
import cn.zxlee.znotifyapi.utils.BeanConvertUtils;
import cn.zxlee.znotifyapi.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-27 00:40
 **/

@Service
public class ProjectServiceImpl implements IProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public List<ProjectVO> list(String token, String keyword) {
        List<ProjectPO> list = projectMapper.list(tokenUtils.getUserIdByToken(token), keyword);
        return BeanConvertUtils.convertListTo(list, ProjectVO::new);
    }

    @Override
    public PageResultVO<ProjectVO> listByPage(String token, ProjectPageBO projectBO) {
        PageResultVO<ProjectVO> pageResultVO = new PageResultVO<>();

        PageHelper.startPage(projectBO.getCurrent(), projectBO.getPageSize());
        List<ProjectPO> projectPOList = projectMapper.list(tokenUtils.getUserIdByToken(token), projectBO.getKeyword());
        PageInfo<ProjectPO> pageInfo = new PageInfo<>(projectPOList);
        pageResultVO.setCurrent(pageInfo.getPageNum());
        pageResultVO.setPageSize(pageInfo.getPageSize());
        pageResultVO.setTotal(pageInfo.getTotal());
        pageResultVO.setResults(BeanConvertUtils.convertListTo(pageInfo.getList(), ProjectVO::new));

        PageHelper.clearPage();
        return pageResultVO;
    }

    @Override
    public int saveProject(String token, ProjectBO projectBO) {
        ProjectPO oldProjectPO = projectMapper.listByName(tokenUtils.getUserIdByToken(token), projectBO.getProjectName());
        if (null != oldProjectPO) {
            throw new CommonException("此项目名称已存在");
        }
        ProjectPO projectPO = BeanConvertUtils.convertTo(projectBO, ProjectPO::new);
        return projectMapper.insertProject(tokenUtils.getUserIdByToken(token), projectPO);
    }

    @Override
    public ProjectBO getOneById(String id) {
        return null;
    }

    @Override
    public int deleteById(String token, String id) {
        ProjectPO projectPO = projectMapper.listById(tokenUtils.getUserIdByToken(token), id);
        if (null == projectPO) {
            throw new CommonException("当前项目不存在");
        }
        return projectMapper.deleteById(id);
    }
}
