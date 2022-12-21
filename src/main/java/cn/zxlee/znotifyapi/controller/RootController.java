package cn.zxlee.znotifyapi.controller;

import cn.zxlee.znotifyapi.annotation.NoLoginAuth;
import cn.zxlee.znotifyapi.pojo.vo.NoticeVO;
import cn.zxlee.znotifyapi.response.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;

/**
 * @program: z-notify-api
 * @description: 访问根路径控制器
 * @author: zxlee
 * @create: 2022-12-21 00:27
 **/

@Controller
@ApiIgnore
public class RootController {
    @RequestMapping("/")
    @NoLoginAuth
    public void visitRoot(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://github.com/SmileZXLee/z-notify-api");
    }
}
