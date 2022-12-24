package cn.zxlee.znotifyapi.service.impl;

import cn.zxlee.znotifyapi.mapper.StatisticsMapper;
import cn.zxlee.znotifyapi.pojo.bo.StatisticsBO;
import cn.zxlee.znotifyapi.pojo.bo.StatisticsPageBO;
import cn.zxlee.znotifyapi.pojo.po.StatisticsPO;
import cn.zxlee.znotifyapi.pojo.vo.*;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.service.IStatisticsService;
import cn.zxlee.znotifyapi.service.base.impl.BaseInProjectServiceImpl;
import cn.zxlee.znotifyapi.utils.BeanConvertUtils;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-12-17 17:15
 **/

@Service
public class StatisticsServiceImpl extends BaseInProjectServiceImpl implements IStatisticsService<StatisticsVO, StatisticsBO, StatisticsPageBO> {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Autowired
    private Ip2regionSearcher ip2regionSearcher;

    @Override
    public List<StatisticsVO> list(Map map) {
        return BeanConvertUtils.convertListTo(statisticsMapper.list(map), StatisticsVO::new);
    }

    @Override
    public PageResultVO<StatisticsVO> listByPage(Map map, StatisticsPageBO pageBO) {
        return baseListByPage(pageBO, map, StatisticsVO::new, () -> statisticsMapper.list(map));
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
    public int publicSaveOne(StatisticsBO bo) {
        checkHasProject(bo.getProjectId());
        bo.setIpRegion(ip2regionSearcher.getAddress(bo.getIp()));
        return saveOne("", bo);
    }

    @Override
    public StatisticsResultVO publicGetStatisticsResult(String projectId, String visitorBy) {
        visitorBy = null == visitorBy ? "ip" : visitorBy;
        int visitorCount = statisticsMapper.countGroupBy(projectId, visitorBy);
        int statisticsListCount = statisticsMapper.listCount(projectId);
        StatisticsResultVO statisticsResultVO = new StatisticsResultVO();
        statisticsResultVO.setViewCount(statisticsListCount);
        statisticsResultVO.setVisitorCount(visitorCount);
        return statisticsResultVO;
    }

    @Override
    public StatisticsAnalysisResultVO getStatisticsAnalysisResult(String token, String projectId, String visitorBy) {
        visitorBy = null == visitorBy ? "ip" : visitorBy;
        checkIsCurrentProject(token, projectId);

        int visitorCount = statisticsMapper.countGroupBy(projectId, visitorBy);
        int todayVisitorCount = statisticsMapper.todayCountGroupBy(projectId, visitorBy);
        int yesterdayVisitorCount = statisticsMapper.yesterdayCountGroupBy(projectId, visitorBy);
        List<StatisticsRegionCountVO> statisticsIpRegionCountVOS = statisticsMapper.ipRegionCountList(projectId);
        int statisticsListCount = statisticsMapper.listCount(projectId);
        int todayListCount = statisticsMapper.todayListCount(projectId);
        int yesterdayListCount = statisticsMapper.yesterdayListCount(projectId);
        int days7ListCount = statisticsMapper.days7ListCount(projectId);
        int lastDays7ListCount = statisticsMapper.lastDays7ListCount(projectId);
        int days30ListCount = statisticsMapper.days30ListCount(projectId);
        List<StatisticsTimeCountVO> statisticsTimeHour24CountVOS = statisticsMapper.hour24CountList(projectId);
        List<StatisticsDateCountVO> statisticsDateDays10CountVOS = statisticsMapper.days10CountList(projectId);
        List<StatisticsDateCountVO> statisticsDateMonths12CountVOS = statisticsMapper.months12CountList(projectId);

        StatisticsAnalysisResultVO statisticsResultVO = new StatisticsAnalysisResultVO();
        statisticsResultVO.setViewCount(statisticsListCount);
        statisticsResultVO.setVisitorCount(visitorCount);
        statisticsResultVO.setTodayVisitorCount(todayVisitorCount);
        statisticsResultVO.setYesterdayVisitorCount(yesterdayVisitorCount);
        statisticsResultVO.setTodayViewCount(todayListCount);
        statisticsResultVO.setYesterdayViewCount(yesterdayListCount);
        statisticsResultVO.setDays7ViewCount(days7ListCount);
        statisticsResultVO.setLastDays7ViewCount(lastDays7ListCount);
        statisticsResultVO.setDays30ViewCount(days30ListCount);
        statisticsResultVO.setHour24CountList(statisticsTimeHour24CountVOS);
        statisticsResultVO.setDays10CountList(statisticsDateDays10CountVOS);
        statisticsResultVO.setMonths12CountList(statisticsDateMonths12CountVOS);
        statisticsResultVO.setIpRegionCountList(statisticsIpRegionCountVOS);
        return statisticsResultVO;
    }
}
