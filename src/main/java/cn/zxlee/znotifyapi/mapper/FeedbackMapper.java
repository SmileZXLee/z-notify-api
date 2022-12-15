package cn.zxlee.znotifyapi.mapper;

import cn.zxlee.znotifyapi.mapper.base.BaseMapper;
import cn.zxlee.znotifyapi.pojo.bo.FeedbackReplyBO;
import cn.zxlee.znotifyapi.pojo.po.FeedbackPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackMapper extends BaseMapper<FeedbackPO> {
    int updateReply(@Param("id") String id, @Param("bo") FeedbackReplyBO bo);
    List<FeedbackPO> listByUsername(String projectId, String username);
}
