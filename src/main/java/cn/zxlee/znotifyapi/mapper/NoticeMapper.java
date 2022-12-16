package cn.zxlee.znotifyapi.mapper;

import cn.zxlee.znotifyapi.mapper.base.BaseMapper;
import cn.zxlee.znotifyapi.pojo.po.NoticePO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper extends BaseMapper<NoticePO> {
    List<NoticePO> listByExipretime(String projectId);
}
