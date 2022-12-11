package cn.zxlee.znotifyapi.utils;

import cn.zxlee.znotifyapi.exception.CommonException;
import cn.zxlee.znotifyapi.utils.redis.RedisCache;
import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: z-notify-api
 * @description: 验证码工具类
 * @author: zxlee
 * @create: 2022-11-29 17:41
 **/

@Component
public class CheckCodeUtils {

    @Autowired
    private RedisCache redisCache;

    public static String getRandomCheckCode() {
        return String.valueOf((int)((Math.random() * 9 + 1) * 100000));
    }

    public void saveCheckCode(String mail, String checkCode) {
        Map<String, Object> cachedCheckCodeMap = getCheckCodeMap(mail);
        if (null != cachedCheckCodeMap) {
            Long passMillis = System.currentTimeMillis() - Long.parseLong(cachedCheckCodeMap.get("timestamp").toString());
            if (passMillis < 60 * 1000) {
                throw new CommonException(String.format("邮箱验证码发送过于频繁，请%d秒后重试", (60 * 1000 - passMillis) / 1000));
            }
        }

        String redisKey = CheckCodeUtils.getRedisKey(mail);
        Map checkCodeMap = new HashMap<String, Object>(2);
        checkCodeMap.put("value", checkCode);
        checkCodeMap.put("timestamp", System.currentTimeMillis());
        redisCache.setCacheObject(redisKey, JSONUtils.toJSONString(checkCodeMap), 5, TimeUnit.MINUTES);
    }

    public String getCheckCode(String mail) {
        Map<String, Object> cachedCheckCodeMap = getCheckCodeMap(mail);
        if (null != cachedCheckCodeMap) {
            return cachedCheckCodeMap.get("value").toString();
        }
        return null;
    }

    private Map getCheckCodeMap(String mail) {
        String redisKey = CheckCodeUtils.getRedisKey(mail);
        Object checkCodeObj = redisCache.getCacheObject(redisKey);
        if (null != checkCodeObj) {
            HashMap<String, Object>checkCodeMap = (HashMap)JSONUtils.parse(checkCodeObj.toString());
            return checkCodeMap;
        } else {
            return null;
        }
    }

    private static String getRedisKey(String mail) {
        return "checkCode:" + mail;
    }

}
