package cn.zxlee.znotifyapi.mapper;

import cn.zxlee.znotifyapi.pojo.po.TextPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextMapper {
    List<TextPO> list(String projectId, String keyword);

    List<TextPO> listByKey(String projectId, String key);

    TextPO listById(String id);

    int insertOne(TextPO textPO);

    int updateOne(@Param("id") String id, @Param("textPO") TextPO textPO);

    int deleteById(String id);
}
