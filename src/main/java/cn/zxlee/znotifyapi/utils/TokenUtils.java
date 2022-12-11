package cn.zxlee.znotifyapi.utils;

import cn.zxlee.znotifyapi.utils.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-26 11:30
 **/

@Component
public class TokenUtils {
    @Autowired
    private RedisCache redisCache;

    public String getUserIdByToken(String token) {
        Object userId = redisCache.getCacheObject(TokenUtils.getRedisKey(token));
        return null == userId ? null : userId.toString();
    }

    public String generateAndSaveToken(String userId) {
        String token = TokenUtils.generateToken(userId);
        String redisKey = TokenUtils.getRedisKey(token);
        Object oldRedisKey = redisCache.getCacheObject(userId);
        if (null != oldRedisKey) {
            redisCache.deleteObject(oldRedisKey.toString());
        }
        redisCache.setCacheObject(redisKey, userId,7, TimeUnit.DAYS);
        redisCache.setCacheObject(userId, redisKey,7, TimeUnit.DAYS);
        return token;
    }

    public String verifyToken(String token) {
        if (StringUtils.hasText(token)) {
            // 如果token存在，通过token从redis从获取用户信息，若用户信息存在，则token校验通过
            String redisKey = TokenUtils.getRedisKey(token);
            String userId = redisCache.getCacheObject(redisKey);
            if (StringUtils.hasText(userId)) {
                return userId;
            }
        }
        return "";
    }

    public boolean removeToken(String token) {
        if (StringUtils.hasText(token)) {
            String redisKey = TokenUtils.getRedisKey(token);
            String userId = redisCache.getCacheObject(redisKey);
            return StringUtils.hasText(userId) && redisCache.deleteObject(userId) && redisCache.deleteObject(redisKey);
        }
        return false;
    }

    private static String generateToken(String userId) {
        return EncryptionUtils.md5Encrypt(userId + SnowFlake.nextId());
    }

    private static String getRedisKey(String token) {
        return "token:" + token;
    }

}
