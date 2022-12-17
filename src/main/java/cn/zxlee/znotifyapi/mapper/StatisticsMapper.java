package cn.zxlee.znotifyapi.mapper;

import cn.zxlee.znotifyapi.mapper.base.BaseMapper;
import cn.zxlee.znotifyapi.pojo.po.StatisticsPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsMapper extends BaseMapper<StatisticsPO> {

    List<StatisticsPO> listGroupByIp(String projectId);

    int listCount(String projectId);
}
