package cn.zxlee.znotifyapi.controller;

import cn.zxlee.znotifyapi.pojo.bo.FeedbackPageBO;
import cn.zxlee.znotifyapi.pojo.bo.FeedbackReplyBO;
import cn.zxlee.znotifyapi.pojo.bo.TextBO;
import cn.zxlee.znotifyapi.pojo.bo.VersionPageBO;
import cn.zxlee.znotifyapi.pojo.vo.VersionVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.response.Result;
import cn.zxlee.znotifyapi.service.IFeedbackService;
import cn.zxlee.znotifyapi.service.IVersionService;
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
 * @description: 反馈管理控制器
 * @author: zxlee
 * @create: 2022-12-13 20:45
 **/

@Slf4j
@RestController
@RequestMapping("/v1/feedback")
@Api(value = "反馈管理", tags = {"反馈管理"})
public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;

    @GetMapping("/feedbacks/{project_id}")
    @ApiOperation("获取项目下的反馈列表")
    public Result<PageResultVO<VersionVO>> getFeedbacks(@RequestHeader String token, @NotEmpty @PathVariable(value = "project_id") String projectId, @Validated FeedbackPageBO bo){
        HashMap<String, String> params = new HashMap<String, String>() {{
            put("token", token);
            put("projectId", projectId);
            put("keyword", bo.getKeyword());
        }};
        return Result.success(feedbackService.listByPage(params, bo));
    }

    @PutMapping("/reply/{id}")
    @ApiOperation("回复用户反馈")
    public Result replayFeedback(@RequestHeader String token, @NotEmpty @PathVariable(value = "id") String id, @Validated @RequestBody FeedbackReplyBO bo){
        int result = feedbackService.updateReply(token, id, bo);
        return result > 0 ? Result.success() : Result.fail("回复失败");
    }

    @DeleteMapping("/feedback/{id}")
    @ApiOperation("删除一条反馈")
    public Result deleteVersion(@RequestHeader String token, @NotEmpty @PathVariable(value = "id") String id) {
        HashMap<String, String> params = new HashMap<String, String>() {{
            put("token", token);
        }};
        int result = feedbackService.deleteById(params, id);
        return result > 0 ? Result.success() : Result.fail("删除失败");
    }
}
