package cn.zxlee.znotifyapi.mapper;

import cn.zxlee.znotifyapi.mapper.base.BaseMapper;
import cn.zxlee.znotifyapi.pojo.po.VersionPO;

import java.util.List;

public interface VersionMapper extends BaseMapper<VersionPO> {
    List<VersionPO> listByVersion(String projectId, String version);
}
