package cn.llman.common.utils;

import lombok.SneakyThrows;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * @author
 * @date 2018/11/15
 */
public class BeanUtils {

    @SneakyThrows
    public static void copyAttribute(Object source, Object dest) {
        org.springframework.beans.BeanUtils.copyProperties(source, dest);
    }

    @SneakyThrows
    public static void copyAttributeMissNull(Object source, Object dest) {
        org.springframework.beans.BeanUtils.copyProperties(source, dest, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
