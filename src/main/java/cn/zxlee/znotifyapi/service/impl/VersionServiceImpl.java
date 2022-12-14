package cn.zxlee.znotifyapi.service.impl;

import cn.zxlee.znotifyapi.exception.CommonException;
import cn.zxlee.znotifyapi.mapper.VersionMapper;
import cn.zxlee.znotifyapi.pojo.bo.VersionBO;
import cn.zxlee.znotifyapi.pojo.bo.VersionPageBO;
import cn.zxlee.znotifyapi.pojo.po.VersionPO;
import cn.zxlee.znotifyapi.pojo.vo.VersionVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.service.IVersionService;
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
 * @create: 2022-12-11 16:29
 **/

@Service
public class VersionServiceImpl extends BaseInProjectServiceImpl implements IVersionService<VersionVO, VersionBO, VersionPageBO> {
    @Autowired
    private VersionMapper versionMapper;

    @Override
    public List<VersionVO> list(Map map) {
        return null;
    }

    @Override
    public PageResultVO<VersionVO> listByPage(Map map, VersionPageBO pageBO) {
        return baseListByPage(pageBO, map, VersionVO::new, () -> versionMapper.list(map));
    }

    @Override
    public int saveOne(String token, VersionBO bo) {
        checkIsCurrentProject(token, bo.getProjectId());
        List<VersionPO> oldPOList = versionMapper.listByVersion(bo.getProjectId(), bo.getVersion());
        if (null != oldPOList && oldPOList.size() > 0) {
            throw new CommonException("此版本号已存在");
        }
        return versionMapper.insertOne(BeanConvertUtils.convertTo(bo, VersionPO::new));
    }

    @Override
    public int updateOne(Map map, String id, VersionBO bo) {
        super.<VersionPO>checkInProjectForUpdate(versionMapper, map.get("token").toString(), id);
        return versionMapper.updateOne(id, BeanConvertUtils.convertTo(bo, VersionPO::new));
    }

    @Override
    public int deleteById(Map map, String id) {
        super.<VersionPO>checkInProjectForUpdate(versionMapper, map.get("token").toString(), id);
        return versionMapper.deleteById(id);
    }

    @Override
    public List<VersionVO> publicListByVersion(String projectId, String version) {
        return BeanConvertUtils.convertListTo(versionMapper.listByHigherVersion(projectId, version), VersionVO::new);
    }

}
