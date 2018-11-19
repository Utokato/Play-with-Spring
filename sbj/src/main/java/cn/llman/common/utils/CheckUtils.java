package cn.llman.common.utils;

import cn.llman.beans.annotation.INotEmpty;
import cn.llman.common.exception.CheckException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * 校验工具类
 *
 * @author
 * @date 2018/11/19
 */
public class CheckUtils {

    public static void check(boolean condition, String msgKey, Object... args) {
        // 入参是 失败条件
        if (condition) {
            fail(msgKey, args);
        }
    }

    public static void notEmpty(String str, String msgKey, Object... args) {
        if (str == null || str.isEmpty()) {
            fail(msgKey, args);
        }
    }

    public static void notNull(Object obj, String msgKey, Object... args) {
        if (obj == null) {
            fail(msgKey, args);
        }
    }

    public static void notAnyEmpty(Object obj) {
        // 获取为空的字段
        List<String> nullFiledList = Arrays.asList(BeanUtils.getNullPropertyNames(obj));

        // 自定义注解来 获取 需要校验的字段
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(INotEmpty.class)) {
                if (nullFiledList.contains(field.getName())) {
                    fail(field.getAnnotation(INotEmpty.class).value(), null);
                }
            }
        }

    }

    private static void fail(String msgKey, Object[] args) {
        throw new CheckException(msgKey);
    }

}
