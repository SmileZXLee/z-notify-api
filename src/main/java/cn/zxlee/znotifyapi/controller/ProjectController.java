package cn.zxlee.znotifyapi.controller;

import cn.zxlee.znotifyapi.pojo.bo.ProjectBO;
import cn.zxlee.znotifyapi.pojo.bo.ProjectPageBO;
import cn.zxlee.znotifyapi.pojo.vo.ProjectVO;
import cn.zxlee.znotifyapi.pojo.vo.base.PageResultVO;
import cn.zxlee.znotifyapi.response.Result;
import cn.zxlee.znotifyapi.service.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;

/**
 * @program: z-notify-api
 * @description: 项目管理控制器
 * @author: zxlee
 * @create: 2022-11-27 00:31
 **/

@Slf4j
@RestController
@RequestMapping("/v1/project")
@Api(value = "项目管理", tags = {"项目管理"})
@Validated
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @GetMapping("/projects")
    @ApiOperation("获取项目列表")
    public Result<PageResultVO<ProjectVO>> getProjects(@RequestHeader String token, @Validated ProjectPageBO bo){
        HashMap<String, String> params  = new HashMap<String, String>() {{
            put("token", token);
        }};
        return Result.success(projectService.listByPage(params, bo));
    }

    @GetMapping("/projects/select")
    @ApiOperation("获取下拉框中使用的项目列表")
    public Result<List<ProjectVO>> getProjectsForSelect(@RequestHeader String token){
        return Result.success(projectService.listForSelect(token));
    }

    @PostMapping("/project")
    @ApiOperation("添加一条项目")
    public Result saveProject(@RequestHeader String token, @Validated @RequestBody ProjectBO bo){
        int result = projectService.saveOne(token, bo);
        return result > 0 ? Result.success() : Result.fail("添加失败");
    }

    @DeleteMapping("/project/{project_id}")
    @ApiOperation("删除一条项目")
    public Result deleteProject(@RequestHeader String token, @NotEmpty @PathVariable("project_id") String projectId){
        HashMap<String, String> params  = new HashMap<String, String>() {{
            put("token", token);
        }};
        int result = projectService.deleteById(params, projectId);
        return result > 0 ? Result.success() : Result.fail("删除失败");
    }
}
