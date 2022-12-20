package cn.zxlee.znotifyapi.service;

import cn.zxlee.znotifyapi.pojo.bo.ProjectBO;
import cn.zxlee.znotifyapi.service.base.IBaseService;

import java.util.List;

public interface IProjectService<V, B, PB> extends IBaseService<V, B, PB> {

    ProjectBO getOneById(String id);

    List listForSelect(String token);
}
