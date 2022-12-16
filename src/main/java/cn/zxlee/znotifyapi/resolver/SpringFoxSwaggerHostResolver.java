package cn.zxlee.znotifyapi.resolver;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: z-notify-api
 * @description: 解决swagger3.0中接口为https协议时servers不正确的问题
 * @author: zxlee
 * @create: 2022-12-16 00:44
 **/

@Component
public class SpringFoxSwaggerHostResolver implements WebMvcOpenApiTransformationFilter {
    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {

        HttpServletRequest request = context.request().get();

        OpenAPI swagger = context.getSpecification();

        String scheme = "http";
        String referer = request.getHeader("Referer");

        if(StringUtils.hasLength(referer)){
            scheme = referer.split(":")[0];
        }

        List<Server> servers = new ArrayList<>();
        String finalScheme = scheme;
        swagger.getServers().forEach(item->{
            item.setUrl(clearDefaultPort(item.getUrl().replace("http", finalScheme)));
            servers.add(item);
        });
        swagger.setServers(servers);
        return swagger;
    }

    private String clearDefaultPort(String url){
        String port = url.split(":")[2];
        if("80".equals(port)||"443".equals(port)){
            return url.replace(":80","").replace(":443","");
        }
        return url;
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return documentationType.equals(DocumentationType.OAS_30);
    }
}
