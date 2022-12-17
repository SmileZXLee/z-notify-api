package cn.zxlee.znotifyapi.service.impl;

import cn.zxlee.znotifyapi.mapper.StatisticsMapper;
import cn.zxlee.znotifyapi.pojo.bo.StatisticsBO;
import cn.zxlee.znotifyapi.pojo.po.StatisticsPO;
import cn.zxlee.znotifyapi.pojo.vo.StatisticsResultVO;
import cn.zxlee.znotifyapi.pojo.vo.StatisticsVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.service.IStatisticsService;
import cn.zxlee.znotifyapi.service.base.impl.BaseInProjectServiceImpl;
import cn.zxlee.znotifyapi.utils.BeanConvertUtils;
import cn.zxlee.znotifyapi.utils.IpUtils;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-12-17 17:15
 **/

@Service
public class StatisticsServiceImpl extends BaseInProjectServiceImpl implements IStatisticsService<StatisticsVO, StatisticsBO, Object> {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Autowired
    private Ip2regionSearcher ip2regionSearcher;

    @Override
    public List<StatisticsVO> list(Map map) {
        return BeanConvertUtils.convertListTo(statisticsMapper.list(map), StatisticsVO::new);
    }

    @Override
    public PageResultVO<StatisticsVO> listByPage(Map map, Object pageBO) {
        return null;
    }

    @Override
    public int saveOne(String token, StatisticsBO bo) {
        return statisticsMapper.insertOne(BeanConvertUtils.convertTo(bo, StatisticsPO::new));
    }

    @Override
    public int updateOne(Map map, String id, StatisticsBO bo) {
        return 0;
    }

    @Override
    public int deleteById(Map map, String id) {
        return 0;
    }

    @Override
    public int publicSaveOne(String ip, String projectId) {
        checkHasProject(projectId);
        StatisticsBO bo = new StatisticsBO();
        bo.setIp(ip);
        bo.setIpRegion(ip2regionSearcher.getAddress(ip));
        bo.setProjectId(projectId);
        return saveOne("", bo);
    }

    @Override
    public StatisticsResultVO publicGetStatisticsResult(String projectId) {
        List<StatisticsPO> statisticsPOS = statisticsMapper.listGroupByIp(projectId);
        int statisticsListCount = statisticsMapper.listCount(projectId);
        StatisticsResultVO statisticsResultVO = new StatisticsResultVO();
        statisticsResultVO.setViewCount(statisticsListCount);
        statisticsResultVO.setVisitorCount(null == statisticsPOS ? 0 : statisticsPOS.size());
        return statisticsResultVO;
    }
}
