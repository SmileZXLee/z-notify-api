package cn.zxlee.znotifyapi.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @program: z-notify-api
 * @description:
 * @author: zxlee
 * @create: 2022-11-26 11:26
 **/
public class EncryptionUtils {
    public static String md5Encrypt(String str) {
        String md5Result = "";
        try {
            md5Result = DigestUtils.md5DigestAsHex(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5Result;
    }
}
