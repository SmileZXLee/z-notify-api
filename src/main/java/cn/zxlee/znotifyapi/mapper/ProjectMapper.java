package cn.zxlee.znotifyapi.mapper;

import cn.zxlee.znotifyapi.pojo.po.ProjectPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper {
    List<ProjectPO> list(String userId, String keyword);

    int insertProject(@Param("userId") String userId, @Param("projectPO") ProjectPO projectPO);

    ProjectPO listByName(String userId, String projectName);

    ProjectPO listById(String userId, String id);

    ProjectPO publicListById(String id);

    int deleteById(String id);
}
