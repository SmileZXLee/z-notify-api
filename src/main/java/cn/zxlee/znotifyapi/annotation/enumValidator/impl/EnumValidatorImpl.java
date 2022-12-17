package cn.zxlee.znotifyapi.annotation.enumValidator.impl;

import cn.zxlee.znotifyapi.annotation.enumValidator.EnumValidator;
import cn.zxlee.znotifyapi.exception.CommonException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @program: z-notify-api
 * @description: 枚举值校验注解实现类
 * @author: zxlee
 * @create: 2022-12-17 21:40
 **/
public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, Object>, Annotation {
    private List<Object> values = new ArrayList<>();

    @Override
    public void initialize(EnumValidator enumValidator) {
        Class<?> clz = enumValidator.value();
        Object[] ojects = clz.getEnumConstants();
        try {
            Method method = clz.getMethod("getValue");
            if (Objects.isNull(method)) {
                throw new CommonException(String.format("枚举对象{}缺少字段名为value的字段", clz.getName()));
            }
            Object value = null;
            for (Object obj : ojects) {
                value = method.invoke(obj);
                values.add(value);
            }
        } catch (Exception e) {
            throw new CommonException(String.format("枚举对象{}处理异常", clz.getName()));
        }
    }


    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(value) || values.contains(value) ? true : false;
    }
}
