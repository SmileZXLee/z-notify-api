package cn.zxlee.znotifyapi.service;

import cn.zxlee.znotifyapi.pojo.bo.TextBO;
import cn.zxlee.znotifyapi.pojo.bo.TextPageBO;
import cn.zxlee.znotifyapi.pojo.vo.TextVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;

import java.util.List;

public interface ITextService {

    List<TextVO> list(String token, String projectId);

    PageResultVO<TextVO> listByPage(String token, String projectId, TextPageBO textPageBO);

    List<TextVO> publicListByKey(String projectId, String key);

    int saveText(String token, TextBO textBO);

    int updateText(String token, String id, TextBO textBO);

    int deleteById(String token, String id);
}
