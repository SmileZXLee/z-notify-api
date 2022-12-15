package cn.zxlee.znotifyapi.utils;

import java.lang.reflect.Method;

/**
 * @program: z-notify-api
 * @description: 数据处理工具类
 * @author: zxlee
 * @create: 2022-12-15 13:00
 **/
public class DataHandleUtils {
    public static Object getFieldValueByName(String fieldName, Object o) {
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        String getter = "get" + firstLetter + fieldName.substring(1);
        Method method = null;
        try {
            method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }
}
