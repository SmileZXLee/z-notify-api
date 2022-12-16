package cn.zxlee.znotifyapi.service.base.impl;

import cn.zxlee.znotifyapi.exception.CommonException;
import cn.zxlee.znotifyapi.mapper.ProjectMapper;
import cn.zxlee.znotifyapi.mapper.base.BaseMapper;
import cn.zxlee.znotifyapi.pojo.bo.base.PageBO;
import cn.zxlee.znotifyapi.pojo.po.ProjectPO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.utils.DataHandleUtils;
import cn.zxlee.znotifyapi.utils.TokenUtils;
import com.github.pagehelper.ISelect;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @program: z-notify-api
 * @description: 基础的项目中的子项Service
 * @author: zxlee
 * @create: 2022-12-11 17:07
 **/
public abstract class BaseInProjectServiceImpl extends BaseServiceImpl {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private TokenUtils tokenUtils;

    protected PageResultVO baseListByPage(PageBO pageBO, Map params, Supplier targetSupplier, ISelect select) {
        checkIsCurrentProject(params.get("token").toString(), params.get("projectId").toString());
        return super.baseListByPage(pageBO, targetSupplier, select);
    }

    /**
     * 查询在当前用户中对应的项目是否存在
     * 必须添加当前用户id的过滤条件，避免绕过用户直接添加通知
     */
    protected void checkIsCurrentProject(String token, String projectId) {
        ProjectPO projectPO = projectMapper.listByUserIdAndId(tokenUtils.getUserIdByToken(token), projectId);
        if (null == projectPO) {
            throw new CommonException("当前项目不存在");
        }
    }

    protected void checkHasProject(String projectId) {
        ProjectPO projectPO = projectMapper.publicListById(projectId);
        if (null == projectPO) {
            throw new CommonException("当前项目不存在");
        }
    }

    protected <P> void checkInProjectForUpdate(BaseMapper<P> mapper, String token, String id) {
        P po = mapper.listById(id);
        if (null == po) {
            throw new CommonException("此id对应数据不存在");
        }
        checkIsCurrentProject(token, DataHandleUtils.getFieldValueByName("projectId", po).toString());
    }
}
