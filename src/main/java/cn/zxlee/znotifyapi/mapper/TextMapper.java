package cn.zxlee.znotifyapi.mapper;

import cn.zxlee.znotifyapi.mapper.base.BaseMapper;
import cn.zxlee.znotifyapi.pojo.po.TextPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextMapper extends BaseMapper<TextPO> {
    List<TextPO> listByKey(String projectId, String key);
}
