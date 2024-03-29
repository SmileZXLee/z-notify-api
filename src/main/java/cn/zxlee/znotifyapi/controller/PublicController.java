package cn.zxlee.znotifyapi.controller;

import cn.zxlee.znotifyapi.annotation.NoLoginAuth;
import cn.zxlee.znotifyapi.annotation.enumValidator.EnumValidator;
import cn.zxlee.znotifyapi.enums.StatisticsBadgeType;
import cn.zxlee.znotifyapi.enums.StatisticsVisitorBy;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public Result<List<NoticeVO>> getNotices(@NotEmpty @PathVariable("project_id") String projectId){
        return Result.success(noticeService.publicList(projectId));
    }

    @GetMapping("/text/{project_id}/{key}")
    @ApiOperation("根据key获取文本")
    @NoLoginAuth
    public Result<TextVO> getTextByKey(@NotEmpty @PathVariable("project_id") String projectId, @NotEmpty @PathVariable("key") String key){
        List<TextVO> textVOS = textService.publicListByKey(projectId, key);
        return Result.success(textVOS.size() > 0 ?  textVOS.get(0) : null);
    }

    @GetMapping("/versions/{project_id}/{version}")
    @ApiOperation("根据版本号获取版本，传当前版本号，会返回高于此版本的所有版本，如果为空则代表没有新版本")
    @NoLoginAuth
    public Result<List<VersionVO>> getVersions(@NotEmpty @PathVariable("project_id") String projectId, @Pattern(regexp = "^\\d(.\\d)*$", message = "版本号格式不合法") @PathVariable("version") String version){
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
    public Result<List<FeedbackVO>> getFeedbacks(@NotEmpty @PathVariable("project_id") String projectId, @PathVariable("username") String username){
        return Result.success(feedbackService.publicListByUsername(projectId, username));
    }

    @PostMapping(value = "/upload/uploadFiles", headers = "content-type=multipart/form-data")
    @ApiOperation("文件上传，支持多个文件同时上传，每个文件上限为1MB")
    @NoLoginAuth
    public Result<List<String>> uploadFiles(@RequestPart("files") MultipartFile[] files) {
        return Result.success(ossService.uploadFiles(files));
    }

    @GetMapping("/statistics/{project_id}/addOnly")
    @ApiOperation("访问一次项目(不返回任何信息，可以放在img标签中并设置display:none来达到无感记录的效果)")
    @NoLoginAuth
    public String visitGetStatistics(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @NotEmpty @PathVariable("project_id") String projectId,
                                     @ApiParam("用于额外区分不同个体的标签") @RequestParam(value = "tag", required = false) String tag,
                                     @ApiParam("访问者来源") @RequestParam(value = "from", required = false) String from){
        response.setHeader("Cache-Control", "no-cache,max-age=0,no-store,s-maxage=0,proxy-revalidate");
        response.setHeader("Expires", "0");
        String ip = IpUtils.getIpAddr(request);
        StatisticsBO bo = new StatisticsBO();
        bo.setProjectId(projectId);
        bo.setIp(ip);
        bo.setTag(tag);
        bo.setFrom(from);
        statisticsService.publicSaveOne(bo);
        return null;
    }

    @GetMapping("/statistics/{project_id}")
    @ApiOperation("访问一次项目并获取项目统计信息")
    @NoLoginAuth
    public Result<StatisticsResultVO> visitAndGetStatistics(HttpServletRequest request,
                                                            @NotEmpty @PathVariable("project_id") String projectId,
                                                            @ApiParam("用于额外区分不同个体的标签") @RequestParam(value = "tag", required = false) String tag,
                                                            @ApiParam("访问者来源") @RequestParam(value = "from", required = false) String from,
                                                            @ApiParam("visitor_count计算根据什么区分，默认为ip，可选值有ip、tag") @RequestParam(value = "visitor_by", required = false) @EnumValidator(StatisticsVisitorBy.class) String visitorBy) {
        String ip = IpUtils.getIpAddr(request);
        StatisticsBO bo = new StatisticsBO();

        bo.setProjectId(projectId);
        bo.setIp(ip);
        bo.setTag(tag);
        bo.setFrom(from);
        statisticsService.publicSaveOne(bo);
        return Result.success(statisticsService.publicGetStatisticsResult(projectId, visitorBy));
    }

    @GetMapping(value = "/statistics/{project_id}/badge", produces="image/svg+xml;charset=utf-8")
    @ApiOperation("访问一次项目并获取项目统计信息以badge形式展示(依赖于shields.io)")
    @NoLoginAuth
    public String visitAndGetStatisticsOnBadge(HttpServletRequest request, HttpServletResponse response, @NotEmpty @PathVariable("project_id") String projectId, @Validated StatisticsBadgeBO badgeBO) {
        response.setHeader("Cache-Control", "no-cache,max-age=0,no-store,s-maxage=0,proxy-revalidate");
        response.setHeader("Expires", "0");

        String ip = IpUtils.getIpAddr(request);
        StatisticsBO bo = new StatisticsBO();
        bo.setProjectId(projectId);
        bo.setIp(ip);
        bo.setTag(badgeBO.getTag());
        bo.setFrom(badgeBO.getFrom());
        statisticsService.publicSaveOne(bo);

        StatisticsResultVO statisticsResultVO = statisticsService.publicGetStatisticsResult(projectId, badgeBO.getVisitorBy());

        String badgeType = badgeBO.getType();
        Integer count = StatisticsBadgeType.VIEW_COUNT.getValue().equals(badgeType) ? statisticsResultVO.getViewCount() : statisticsResultVO.getVisitorCount();

        return thirdPartyApiService.getBadge(badgeBO.getTitle(), count.toString(), badgeBO.getColor(), badgeBO.getStyle());
    }
}
