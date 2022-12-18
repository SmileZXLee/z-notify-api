package cn.zxlee.znotifyapi.service;

import cn.zxlee.znotifyapi.pojo.vo.StatisticsAnalysisResultVO;
import cn.zxlee.znotifyapi.pojo.vo.StatisticsResultVO;
import cn.zxlee.znotifyapi.service.base.IBaseService;


public interface IStatisticsService<V, B, PB> extends IBaseService<V, B, PB> {
    int publicSaveOne(String ip, String projectId);

    StatisticsResultVO publicGetStatisticsResult(String projectId);

    StatisticsAnalysisResultVO getStatisticsAnalysisResult(String token, String projectId);
}
