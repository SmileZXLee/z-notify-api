package cn.zxlee.znotifyapi.mapper;

import cn.zxlee.znotifyapi.mapper.base.BaseMapper;
import cn.zxlee.znotifyapi.pojo.po.ProjectPO;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectMapper extends BaseMapper<ProjectPO> {

    ProjectPO listByName(String userId, String projectName);

    ProjectPO listByUserIdAndId(String userId, String id);

    ProjectPO publicListById(String id);

}
