package cn.zxlee.znotifyapi.service.impl;

import cn.zxlee.znotifyapi.exception.CommonException;
import cn.zxlee.znotifyapi.mapper.TextMapper;
import cn.zxlee.znotifyapi.pojo.bo.TextBO;
import cn.zxlee.znotifyapi.pojo.bo.TextPageBO;
import cn.zxlee.znotifyapi.pojo.po.TextPO;
import cn.zxlee.znotifyapi.pojo.vo.TextVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.service.ITextService;
import cn.zxlee.znotifyapi.service.base.impl.BaseInProjectServiceImpl;
import cn.zxlee.znotifyapi.utils.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-12-07 19:57
 **/

@Service
public class TextServiceImpl extends BaseInProjectServiceImpl implements ITextService<TextVO, TextBO, TextPageBO> {

    @Autowired
    private TextMapper textMapper;

    @Override
    public List<TextVO> list(Map map) {
        return null;
    }

    @Override
    public PageResultVO<TextVO> listByPage(Map map, TextPageBO pageBO) {
        return baseListByPage(pageBO, map, TextVO::new, () -> textMapper.list(map));

    }

    @Override
    public List<TextVO> publicListByKey(String projectId, String key) {
        return BeanConvertUtils.convertListTo(textMapper.listByKey(projectId, key), TextVO::new);
    }

    @Override
    public int saveOne(String token, TextBO textBO) {
        checkIsCurrentProject(token, textBO.getProjectId());
        List<TextPO> oldTextPOList = textMapper.listByKey(textBO.getProjectId(), textBO.getKey());
        if (null != oldTextPOList && oldTextPOList.size() > 0) {
            throw new CommonException("此key已存在");
        }
        return textMapper.insertOne(BeanConvertUtils.convertTo(textBO, TextPO::new));
    }

    @Override
    public int updateOne(Map map, String id, TextBO bo) {
        super.<TextPO>checkInProjectForUpdate(textMapper, map.get("token").toString(), id);
        return textMapper.updateOne(id, BeanConvertUtils.convertTo(bo, TextPO::new));
    }

    @Override
    public int deleteById(Map map, String id) {
        super.<TextPO>checkInProjectForUpdate(textMapper, map.get("token").toString(), id);
        return textMapper.deleteById(id);
    }
}
