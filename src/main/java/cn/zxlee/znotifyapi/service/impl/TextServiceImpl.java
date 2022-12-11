package cn.zxlee.znotifyapi.service.impl;

import cn.zxlee.znotifyapi.exception.CommonException;
import cn.zxlee.znotifyapi.mapper.ProjectMapper;
import cn.zxlee.znotifyapi.mapper.TextMapper;
import cn.zxlee.znotifyapi.pojo.bo.TextBO;
import cn.zxlee.znotifyapi.pojo.bo.TextPageBO;
import cn.zxlee.znotifyapi.pojo.po.ProjectPO;
import cn.zxlee.znotifyapi.pojo.po.TextPO;
import cn.zxlee.znotifyapi.pojo.vo.TextVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.service.ITextService;
import cn.zxlee.znotifyapi.utils.BeanConvertUtils;
import cn.zxlee.znotifyapi.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-12-07 19:57
 **/

@Service
public class TextServiceImpl implements ITextService {

    @Autowired
    private TextMapper textMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public List<TextVO> list(String token, String projectId) {
        return null;
    }

    @Override
    public PageResultVO<TextVO> listByPage(String token, String projectId, TextPageBO textPageBO) {
        checkIsCurrentProject(token, projectId);

        PageResultVO<TextVO> pageResultVO = new PageResultVO<>();

        PageHelper.startPage(textPageBO.getCurrent(), textPageBO.getPageSize());
        List<TextPO> textPOList = textMapper.list(projectId, textPageBO.getKeyword());
        PageInfo<TextPO> pageInfo = new PageInfo<>(textPOList);
        pageResultVO.setCurrent(pageInfo.getPageNum());
        pageResultVO.setPageSize(pageInfo.getPageSize());
        pageResultVO.setTotal(pageInfo.getTotal());
        pageResultVO.setResults(BeanConvertUtils.convertListTo(pageInfo.getList(), TextVO::new));

        PageHelper.clearPage();
        return pageResultVO;

    }

    @Override
    public List<TextVO> publicListByKey(String projectId, String key) {
        return BeanConvertUtils.convertListTo(textMapper.listByKey(projectId, key), TextVO::new);
    }

    @Override
    public int saveText(String token, TextBO textBO) {
        checkIsCurrentProject(token, textBO.getProjectId());
        List<TextPO> oldTextPOList = textMapper.listByKey(textBO.getProjectId(), textBO.getKey());
        if (null != oldTextPOList && oldTextPOList.size() > 0) {
            throw new CommonException("此key已存在");
        }
        TextPO textPO = BeanConvertUtils.convertTo(textBO, TextPO::new);
        return textMapper.insertOne(textPO);
    }

    @Override
    public int updateText(String token, String id, TextBO textBO) {
        TextPO oldTextPO = textMapper.listById(id);
        if (null == oldTextPO) {
            throw new CommonException("此文本不存在");
        }
        checkIsCurrentProject(token, oldTextPO.getProjectId());
        TextPO textPO = BeanConvertUtils.convertTo(textBO, TextPO::new);
        return textMapper.updateOne(id, textPO);
    }

    @Override
    public int deleteById(String token, String id) {
        TextPO textPO = textMapper.listById(id);
        if (null == textPO) {
            throw new CommonException("此文本不存在");
        }
        checkIsCurrentProject(token, textPO.getProjectId());
        return textMapper.deleteById(id);
    }

    /**
     * 查询在当前用户中对应的项目是否存在
     * 必须添加当前用户id的过滤条件，避免绕过用户直接添加通知
     */
    private void checkIsCurrentProject(String token, String projectId) {
        ProjectPO projectPO = projectMapper.listById(tokenUtils.getUserIdByToken(token), projectId);
        if (null == projectPO) {
            throw new CommonException("当前项目不存在");
        }
    }
}
