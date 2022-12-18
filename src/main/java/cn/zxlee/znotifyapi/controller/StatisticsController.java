package cn.zxlee.znotifyapi.controller;

import cn.zxlee.znotifyapi.pojo.bo.StatisticsPageBO;
import cn.zxlee.znotifyapi.pojo.vo.StatisticsAnalysisResultVO;
import cn.zxlee.znotifyapi.pojo.vo.VersionVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.response.Result;
import cn.zxlee.znotifyapi.service.IStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;

/**
 * @program: z-notify-api
 * @description: 统计管理控制器
 * @author: zxlee
 * @create: 2022-12-07 20:03
 **/

@Slf4j
@RestController
@RequestMapping("/v1/statistics")
@Api(value = "统计管理", tags = {"统计管理"})
@Validated
public class StatisticsController {
    @Autowired
    private IStatisticsService statisticsService;

    @GetMapping("/statistics/{project_id}")
    @ApiOperation("获取项目下的访问数据列表")
    public Result<PageResultVO<VersionVO>> getStatistics(@RequestHeader String token, @NotEmpty @PathVariable(value = "project_id") String projectId, @Validated StatisticsPageBO bo){
        HashMap<String, String> params = new HashMap<String, String>() {{
            put("token", token);
            put("projectId", projectId);
            put("keyword", bo.getKeyword());
        }};
        return Result.success(statisticsService.listByPage(params, bo));
    }

    @GetMapping("/analysis/{project_id}")
    @ApiOperation("获取项目下的访问数据分析")
    public Result<StatisticsAnalysisResultVO> getStatisticsAnalysis(@RequestHeader String token, @NotEmpty @PathVariable(value = "project_id") String projectId){
        return Result.success(statisticsService.getStatisticsAnalysisResult(token, projectId));
    }
}
