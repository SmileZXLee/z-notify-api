package cn.zxlee.znotifyapi.controller;

import cn.zxlee.znotifyapi.pojo.bo.NoticeBO;
import cn.zxlee.znotifyapi.pojo.bo.NoticePageBO;
import cn.zxlee.znotifyapi.pojo.vo.NoticeVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.response.Result;
import cn.zxlee.znotifyapi.service.INoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @program: z-notify-api
 * @description: 通知管理控制器
 * @author: zxlee
 * @create: 2022-11-27 13:53
 **/

@Slf4j
@RestController
@RequestMapping("/v1/notice")
@Api(value = "通知管理", tags = {"通知管理"})
@Validated
public class NoticeController {
    @Autowired
    private INoticeService noticeService;

    @GetMapping("/notices/{project_id}")
    @ApiOperation("获取项目下的通知列表")
    public Result<PageResultVO<NoticeVO>> getNotices(@RequestHeader String token, @NotEmpty @PathVariable(value = "project_id") String projectId, @Validated NoticePageBO bo){
        return Result.success(noticeService.listByPage(token, projectId, bo));
    }

    @PostMapping("/notice")
    @ApiOperation("添加一条通知")
    public Result saveNotice(@RequestHeader String token, @Validated @RequestBody NoticeBO bo){
        int result = noticeService.saveNotice(token, bo);
        return result > 0 ? Result.success() : Result.fail("添加失败");
    }

    @PutMapping("/notice/{id}")
    @ApiOperation("更新一条通知")
    public Result updateNotice(@RequestHeader String token, @NotEmpty @PathVariable(value = "id") String noticeId, @Validated @RequestBody NoticeBO bo){
        int result = noticeService.updateNotice(token, noticeId, bo);
        return result > 0 ? Result.success() : Result.fail("更新失败");
    }

    @DeleteMapping("/notice/{id}")
    @ApiOperation("删除一条通知")
    public Result deleteNotice(@RequestHeader String token, @NotEmpty @PathVariable(value = "id") String noticeId){
        int result = noticeService.deleteById(token, noticeId);
        return result > 0 ? Result.success() : Result.fail("删除失败");
    }
}
