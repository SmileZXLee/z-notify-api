package cn.zxlee.znotifyapi.mapper;

import cn.zxlee.znotifyapi.mapper.base.BaseMapper;
import cn.zxlee.znotifyapi.pojo.vo.StatisticsDateCountVO;
import cn.zxlee.znotifyapi.pojo.po.StatisticsPO;
import cn.zxlee.znotifyapi.pojo.vo.StatisticsRegionCountVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsMapper extends BaseMapper<StatisticsPO> {

    List<StatisticsPO> listGroupByIp(String projectId);

    List<StatisticsPO> todayListGroupByIp(String projectId);

    List<StatisticsPO> yesterdayListGroupByIp(String projectId);

    List<StatisticsRegionCountVO> ipRegionCountList(String projectId);

    int listCount(String projectId);

    int todayListCount(String projectId);

    int yesterdayListCount(String projectId);

    int days7ListCount(String projectId);

    int days30ListCount(String projectId);

    List<StatisticsDateCountVO> days10CountList(String projectId);

    List<StatisticsDateCountVO> months12CountList(String projectId);
}
