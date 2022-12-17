package cn.zxlee.znotifyapi.controller;

import cn.zxlee.znotifyapi.annotation.NoLoginAuth;
import cn.zxlee.znotifyapi.enums.StatisticsBadgeType;
import cn.zxlee.znotifyapi.pojo.bo.FeedbackBO;
import cn.zxlee.znotifyapi.pojo.bo.StatisticsBO;
import cn.zxlee.znotifyapi.pojo.bo.StatisticsBadgeBO;
import cn.zxlee.znotifyapi.pojo.vo.*;
import cn.zxlee.znotifyapi.response.Result;
import cn.zxlee.znotifyapi.service.*;
import cn.zxlee.znotifyapi.utils.IpUtils;
import cn.zxlee.znotifyapi.utils.oss.IOssService;
import cn.zxlee.znotifyapi.utils.thirdPartyApi.IThirdPartyApiService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @program: z-notify-api
 * @description: 公用接口控制器
 * @author: zxlee
 * @create: 2022-11-25 17:41
 **/

@Slf4j
@RestController
@RequestMapping("/v1/public")
@Api(value = "公共接口", tags = {"公共接口"})
@Validated
public class PublicController {

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private ITextService textService;

    @Autowired
    private IVersionService versionService;

    @Autowired
    private IFeedbackService feedbackService;

    @Autowired
    private IStatisticsService statisticsService;

    @Autowired
    private IOssService ossService;

    @Autowired
    private IThirdPartyApiService thirdPartyApiService;

    @GetMapping("/notices/{project_id}")
    @ApiOperation("获取通知列表")
    @NoLoginAuth
    public Result<List<NoticeVO>> getNotices(@NotEmpty @PathVariable(value = "project_id") String projectId){
        return Result.success(noticeService.publicList(projectId));
    }

    @GetMapping("/text/{project_id}/{key}")
    @ApiOperation("根据key获取文本")
    @NoLoginAuth
    public Result<TextVO> getTextByKey(@NotEmpty @PathVariable(value = "project_id") String projectId, @NotEmpty @PathVariable(value = "key") String key){
        List<TextVO> textVOS = textService.publicListByKey(projectId, key);
        return Result.success(textVOS.size() > 0 ?  textVOS.get(0) : null);
    }

    @GetMapping("/versions/{project_id}/{version}")
    @ApiOperation("根据版本号获取版本，传当前版本号，会返回高于此版本的所有版本，如果为空则代表没有新版本")
    @NoLoginAuth
    public Result<List<VersionVO>> getVersions(@NotEmpty @PathVariable(value = "project_id") String projectId, @Pattern(regexp = "^\\d(.\\d)*$", message = "版本号格式不合法") @PathVariable(value = "version") String version){
        return Result.success(versionService.publicListByVersion(projectId, version));
    }

    @PostMapping("/feedback/feedback")
    @ApiOperation("添加反馈数据")
    @NoLoginAuth
    public Result saveFeedback(@Validated @RequestBody FeedbackBO bo){
        int result = feedbackService.publicSaveOne(bo);
        return result > 0 ? Result.success() : Result.fail("添加失败");
    }

    @GetMapping("/feedbacks/{project_id}/{username}")
    @ApiOperation("查询某个用户下的反馈列表")
    @NoLoginAuth
    public Result<List<FeedbackVO>> getFeedbacks(@NotEmpty @PathVariable(value = "project_id") String projectId, @PathVariable(value = "username") String username){
        return Result.success(feedbackService.publicListByUsername(projectId, username));
    }

    @PostMapping(value = "/upload/uploadFiles", headers = "content-type=multipart/form-data")
    @ApiOperation("文件上传，支持多个文件同时上传，每个文件上限为1MB")
    @NoLoginAuth
    public Result<List<String>> uploadFiles(@RequestPart(value = "files", required = true) MultipartFile[] files) {
        return Result.success(ossService.uploadFiles(files));
    }

    @GetMapping("/statistics/{project_id}")
    @ApiOperation("访问一次项目并获取项目统计信息")
    @NoLoginAuth
    public Result<StatisticsResultVO> visitAndGetStatistics(HttpServletRequest request, @NotEmpty @PathVariable(value = "project_id") String projectId){
        String ip = IpUtils.getIpAddr(request);
        statisticsService.publicSaveOne(ip, projectId);
        return Result.success(statisticsService.publicGetStatisticsResult(projectId));
    }

    @GetMapping("/statistics/{project_id}/badge")
    @ApiOperation("访问一次项目并获取项目统计信息以badge形式展示")
    @NoLoginAuth
    public String visitAndGetStatisticsOnBadge(HttpServletRequest request, @NotEmpty @PathVariable(value = "project_id") String projectId, @Validated StatisticsBadgeBO bo) {
        String ip = IpUtils.getIpAddr(request);
        statisticsService.publicSaveOne(ip, projectId);

        StatisticsResultVO statisticsResultVO = statisticsService.publicGetStatisticsResult(projectId);

        String badgeType = bo.getType();
        Integer count = StatisticsBadgeType.VIEW_COUNT.getValue().equals(badgeType)  ? statisticsResultVO.getViewCount() : statisticsResultVO.getVisitorCount();

        return thirdPartyApiService.getBadge(bo.getTitle(), count.toString(), bo.getColor(), bo.getStyle());
    }
}
