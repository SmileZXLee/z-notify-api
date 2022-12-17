package cn.zxlee.znotifyapi.utils.thirdPartyApi.impl;

import cn.zxlee.znotifyapi.utils.thirdPartyApi.IThirdPartyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @program: z-notify-api
 * @description: 第三方Api实现类
 * @author: zxlee
 * @create: 2022-12-17 21:09
 **/

@Service
public class ThirdPartyApiServiceImpl implements IThirdPartyApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getBadge(String leftTile, String rightTitle, String color, String style) {
        String url = String.format("https://img.shields.io/badge/%s-%s-%s?style=%s", leftTile, rightTitle, color, style);
        HttpHeaders headers = new HttpHeaders();
        String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1";
        headers.add("User-Agent", userAgent);
        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<MultiValueMap<String, Object>>(headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, formEntity, String.class);
        return result.getBody();
    }
}
