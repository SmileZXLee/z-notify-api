package cn.zxlee.znotifyapi.mapper;

import cn.zxlee.znotifyapi.pojo.po.NoticePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper {
    List<NoticePO> list(String projectId);
    List<NoticePO> listByExipretime(String projectId);

    int insertNotice(NoticePO noticePO);

    int updateNotice(@Param("id") String id, @Param("noticePO") NoticePO noticePO);

    NoticePO listById(String id);

    int deleteById(String id);
}
