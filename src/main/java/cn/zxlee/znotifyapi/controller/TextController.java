package cn.zxlee.znotifyapi.controller;

import cn.zxlee.znotifyapi.pojo.bo.TextBO;
import cn.zxlee.znotifyapi.pojo.bo.TextPageBO;
import cn.zxlee.znotifyapi.pojo.vo.TextVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.response.Result;
import cn.zxlee.znotifyapi.service.ITextService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

/**
 * @program: z-notify-api
 * @description: 文本管理控制器
 * @author: zxlee
 * @create: 2022-12-07 20:03
 **/

@Slf4j
@RestController
@RequestMapping("/v1/text")
@Api(value = "文本管理", tags = {"文本管理"})
@Validated
public class TextController {
    @Autowired
    private ITextService textService;

    @GetMapping("/texts/{project_id}")
    @ApiOperation("获取项目下的文本列表")
    public Result<PageResultVO<TextVO>> getTexts(@RequestHeader String token, @NotEmpty @PathVariable(value = "project_id") String projectId, @Validated TextPageBO bo){
        return Result.success(textService.listByPage(token, projectId, bo));
    }

    @PostMapping("/text")
    @ApiOperation("添加一条文本")
    public Result saveText(@RequestHeader String token, @Validated @RequestBody TextBO bo){
        int result = textService.saveText(token, bo);
        return result > 0 ? Result.success() : Result.fail("添加失败");
    }

    @PutMapping("/text/{id}")
    @ApiOperation("更新一条文本")
    public Result updateText(@RequestHeader String token, @NotEmpty @PathVariable(value = "id") String textId, @Validated @RequestBody TextBO bo){
        int result = textService.updateText(token, textId, bo);
        return result > 0 ? Result.success() : Result.fail("更新失败");
    }

    @DeleteMapping("/text/{id}")
    @ApiOperation("删除一条文本")
    public Result deleteNotice(@RequestHeader String token, @NotEmpty @PathVariable(value = "id") String textId){
        int result = textService.deleteById(token, textId);
        return result > 0 ? Result.success() : Result.fail("删除失败");
    }
}
