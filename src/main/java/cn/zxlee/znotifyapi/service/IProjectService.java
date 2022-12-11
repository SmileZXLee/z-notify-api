package cn.zxlee.znotifyapi.service;

import cn.zxlee.znotifyapi.pojo.bo.ProjectBO;
import cn.zxlee.znotifyapi.pojo.bo.ProjectPageBO;
import cn.zxlee.znotifyapi.pojo.vo.ProjectVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;

import java.util.List;

public interface IProjectService {

    List<ProjectVO> list(String token, String keyword);

    PageResultVO<ProjectVO> listByPage(String token, ProjectPageBO projectBO);

    int saveProject(String token, ProjectBO projectBO);

    ProjectBO getOneById(String id);

    int deleteById(String token, String id);
}
