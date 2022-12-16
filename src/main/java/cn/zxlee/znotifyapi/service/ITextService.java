package cn.zxlee.znotifyapi.service;

import cn.zxlee.znotifyapi.pojo.vo.TextVO;
import cn.zxlee.znotifyapi.service.base.IBaseService;

import java.util.List;

public interface ITextService<V, B, PB> extends IBaseService<V, B, PB> {
    List<TextVO> publicListByKey(String projectId, String key);
}
