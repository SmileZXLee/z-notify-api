package cn.zxlee.znotifyapi.controller;

import cn.zxlee.znotifyapi.pojo.bo.VersionBO;
import cn.zxlee.znotifyapi.pojo.bo.VersionPageBO;
import cn.zxlee.znotifyapi.pojo.vo.VersionVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.response.Result;
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
 * @description: 版本管理控制器
 * @author: zxlee
 * @create: 2022-12-07 20:03
 **/

@Slf4j
@RestController
@RequestMapping("/v1/version")
@Api(value = "版本管理", tags = {"版本管理"})
@Validated
public class VersionController {
    @Autowired
    private IVersionService versionService;

    @GetMapping("/versions/{project_id}")
    @ApiOperation("获取项目下的版本列表")
    public Result<PageResultVO<VersionVO>> getVersions(@RequestHeader String token, @NotEmpty @PathVariable(value = "project_id") String projectId, @Validated VersionPageBO bo){
        HashMap<String, String> params = new HashMap<String, String>() {{
            put("token", token);
            put("projectId", projectId);
            put("keyword", bo.getKeyword());
        }};
        return Result.success(versionService.listByPage(params, bo));
    }

    @PostMapping("/version")
    @ApiOperation("添加一个版本")
    public Result saveVersion(@RequestHeader String token, @Validated @RequestBody VersionBO bo){
        int result = versionService.saveOne(token, bo);
        return result > 0 ? Result.success() : Result.fail("添加失败");
    }

    @PutMapping("/version/{id}")
    @ApiOperation("更新一个版本")
    public Result updateVersion(@RequestHeader String token, @NotEmpty @PathVariable(value = "id") String id, @Validated @RequestBody VersionBO bo){
        HashMap<String, String> params  = new HashMap<String, String>() {{
            put("token", token);
        }};
        int result = versionService.updateOne(params, id, bo);
        return result > 0 ? Result.success() : Result.fail("更新失败");
    }

    @DeleteMapping("/version/{id}")
    @ApiOperation("删除一个版本")
    public Result deleteVersion(@RequestHeader String token, @NotEmpty @PathVariable(value = "id") String textId) {
        HashMap<String, String> params = new HashMap<String, String>() {{
            put("token", token);
        }};
        int result = versionService.deleteById(params, textId);
        return result > 0 ? Result.success() : Result.fail("删除失败");
    }
}
