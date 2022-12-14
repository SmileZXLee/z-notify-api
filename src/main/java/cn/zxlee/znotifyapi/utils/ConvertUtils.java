package cn.zxlee.znotifyapi.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: z-notify-api
 * @description: 数据转换工具类
 * @author: zxlee
 * @create: 2022-12-13 19:52
 **/
public class ConvertUtils {

    public static String imgList2Str(List imgList) {
        if (null == imgList && imgList.isEmpty()) {
            return null;
        }
        return imgList.stream().collect(Collectors.joining(";")).toString();
    }

    public static List imgStr2List(String imgStr) {
        if (null != imgStr) {
            return Arrays.asList(imgStr.split(";"));
        }
        return null;
    }
}
