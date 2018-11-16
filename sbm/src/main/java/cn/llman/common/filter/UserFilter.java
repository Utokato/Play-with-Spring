package cn.llman.common.filter;

import cn.llman.common.utils.UserUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 业务中没有此功能
 * <p>
 * 仅此学习
 * <p>
 * 这里只是学习了一下，所以没有真正配置该过滤器
 * 所以该过滤器其实是不起作用的
 * <p>
 * 这个过滤器主要从session中获取用户信息
 * 从cookie中获取 语言相关的信息
 * 将这些信息都放置在ThreadLocal中
 *
 * @author
 * @date 2018/11/15
 */
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 得到用户个人相关的信息(登录的用户，用户使用的语言)
        fillUserInfo((HttpServletRequest) request);

        try {
            chain.doFilter(request, response);
        } finally {
            // 由于tomcat线程需要重用，记得清空
            clearAllUserInfo();
        }

    }

    // 调用 用户工具类 清空用户相关的信息
    private void clearAllUserInfo() {
        UserUtils.clearAllUserInfo();
    }

    private void fillUserInfo(HttpServletRequest request) {

        // 从session中获取用户信息
        String user = getUserFromSession(request);

        if (user != null) {
            UserUtils.setUser(user);
        }

        // 从cookie中获取语言信息
        String locale = getLocaleFromCookies(request);

        // 放到threadlocal，同一个线程任何地方都可以取到
        if (locale != null) {
            UserUtils.setLocale(locale);
        }

    }

    private String getLocaleFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (UserUtils.KEY_LANG.equals(cookies[i].getName())) {
                return cookies[i].getName();
            }
        }
        return null;
    }

    private String getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        // 从session中获取用户信息放到工具类中
        return (String) session.getAttribute(UserUtils.KEY_USER);
    }

    @Override
    public void destroy() {

    }

}
