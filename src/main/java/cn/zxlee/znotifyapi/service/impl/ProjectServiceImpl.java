package cn.zxlee.znotifyapi.service.impl;

import cn.zxlee.znotifyapi.exception.CommonException;
import cn.zxlee.znotifyapi.mapper.ProjectMapper;
import cn.zxlee.znotifyapi.pojo.bo.ProjectBO;
import cn.zxlee.znotifyapi.pojo.bo.ProjectPageBO;
import cn.zxlee.znotifyapi.pojo.po.ProjectPO;
import cn.zxlee.znotifyapi.pojo.vo.ProjectVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.service.IProjectService;
import cn.zxlee.znotifyapi.service.base.impl.BaseServiceImpl;
import cn.zxlee.znotifyapi.utils.BeanConvertUtils;
import cn.zxlee.znotifyapi.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-27 00:40
 **/

@Service
public class ProjectServiceImpl extends BaseServiceImpl implements IProjectService<ProjectVO, ProjectBO, ProjectPageBO> {
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public List<ProjectVO> list(Map map) {
        HashMap<String, String> params  = new HashMap<String, String>() {{
            put("userId", tokenUtils.getUserIdByToken(map.get("token").toString()));
            put("keyword", map.get("keyword").toString());
        }};
        return BeanConvertUtils.convertListTo(projectMapper.list(params), ProjectVO::new);
    }

    @Override
    public PageResultVO<ProjectVO> listByPage(Map map, ProjectPageBO pageBO) {
        map.put("userId", tokenUtils.getUserIdByToken(map.get("token").toString()));
        return baseListByPage(pageBO, ProjectVO::new, () -> projectMapper.list(map));
    }

    @Override
    public int saveOne(String token, ProjectBO projectBO) {
        ProjectPO oldProjectPO = projectMapper.listByName(tokenUtils.getUserIdByToken(token), projectBO.getProjectName());
        if (null != oldProjectPO) {
            throw new CommonException("此项目名称已存在");
        }
        ProjectPO projectPO = BeanConvertUtils.convertTo(projectBO, ProjectPO::new);
        projectPO.setUserId(tokenUtils.getUserIdByToken(token));
        return projectMapper.insertOne(projectPO);
    }

    @Override
    public int updateOne(Map map, String id, ProjectBO bo) {
        return 0;
    }

    @Override
    public ProjectBO getOneById(String id) {
        return null;
    }

    @Override
    public List listForSelect(String token) {
        return projectMapper.listForSelect(tokenUtils.getUserIdByToken(token));
    }

    @Override
    public int deleteById(Map map, String id) {
        ProjectPO projectPO = projectMapper.listByUserIdAndId(tokenUtils.getUserIdByToken(map.get("token").toString()), id);
        if (null == projectPO) {
            throw new CommonException("当前项目不存在");
        }
        return projectMapper.deleteById(id);
    }
}
