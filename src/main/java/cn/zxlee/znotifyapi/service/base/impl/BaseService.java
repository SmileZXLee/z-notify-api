package cn.zxlee.znotifyapi.service.base.impl;

import cn.zxlee.znotifyapi.pojo.bo.base.PageBO;
import cn.zxlee.znotifyapi.pojo.vo.ProjectVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.service.base.IBaseService;
import cn.zxlee.znotifyapi.utils.BeanConvertUtils;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-12-11 16:08
 **/
public abstract class BaseService {

    public PageResultVO baseListByPage(PageBO pageBO, Supplier targetSupplier, ISelect select) {
        PageResultVO<ProjectVO> pageResultVO = new PageResultVO<>();

        PageInfo<Object> pageInfo = PageHelper.startPage(pageBO.getCurrent(), pageBO.getPageSize())
                .doSelectPageInfo(select);
        pageResultVO.setCurrent(pageInfo.getPageNum());
        pageResultVO.setPageSize(pageInfo.getPageSize());
        pageResultVO.setTotal(pageInfo.getTotal());
        pageResultVO.setResults(BeanConvertUtils.convertListTo(pageInfo.getList(), targetSupplier));
        PageHelper.clearPage();

        return pageResultVO;
    }
}
