package cn.zxlee.znotifyapi.controller;

import cn.zxlee.znotifyapi.annotation.NoLoginAuth;
import cn.zxlee.znotifyapi.pojo.vo.NoticeVO;
import cn.zxlee.znotifyapi.pojo.vo.TextVO;
import cn.zxlee.znotifyapi.pojo.vo.VersionVO;
import cn.zxlee.znotifyapi.response.Result;
import cn.zxlee.znotifyapi.service.INoticeService;
import cn.zxlee.znotifyapi.service.ITextService;
import cn.zxlee.znotifyapi.service.IVersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @program: z-notify-api
 * @description: 公用接口控制器RequestBody
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
}
