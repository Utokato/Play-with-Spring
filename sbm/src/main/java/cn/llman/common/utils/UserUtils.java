package cn.llman.common.utils;

import cn.llman.common.exception.UnLoginException;
import org.slf4j.MDC;

import java.util.Locale;

/**
 * 用户工具类
 * <p>
 * 仅此学习
 *
 * @author
 * @date 2018/11/15
 */
public class UserUtils {

    private final static ThreadLocal<String> tlUser = new ThreadLocal<>();
    private final static ThreadLocal<Locale> tlLocale = new ThreadLocal<Locale>() {
        @Override
        protected Locale initialValue() {
            // 语言默认为 中文
            return Locale.CHINESE;
        }
    };

    public static final String KEY_LANG = "lang";
    public static final String KEY_USER = "user";

    public static void setUser(String userId) {
        tlUser.set(userId);
        // 把用户信息放到 logback 中
        // MDC Mapped Diagnostic Context
        MDC.put(KEY_USER, userId);
    }

    public static String getUserIfLogin() {
        return tlUser.get();
    }

    public static String getUser() {
        String user = tlUser.get();
        // 将异常抛出去，然后在aop中统一处理
        if (user == null) {
            throw new UnLoginException();
        }
        return user;
    }

    public static void setLocale(String locale) {
        setLocale(new Locale(locale));
    }

    public static void setLocale(Locale locale) {
        tlLocale.set(locale);
    }

    public static Locale getLocale() {
        return tlLocale.get();
    }

    public static void clearAllUserInfo() {
        tlUser.remove();
        tlLocale.remove();
        MDC.remove(KEY_USER);
    }

}
