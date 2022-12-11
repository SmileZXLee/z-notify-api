package cn.zxlee.znotifyapi.service;

import cn.zxlee.znotifyapi.pojo.vo.VersionVO;
import cn.zxlee.znotifyapi.service.base.IBaseService;

import java.util.List;

public interface IVersionService<V, B, PB> extends IBaseService<V, B, PB> {
    List<VersionVO> publicListByVersion(String projectId, String version);
}
