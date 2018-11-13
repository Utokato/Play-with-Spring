package cn.llman.utils;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 封装雪花算法生成id
 *
 * @author
 * @date 2018/11/12
 */
public class KeyUtils {

    public static String nextId() {
        IdWorker idWorker = new IdWorker();
        return String.valueOf(idWorker.nextId());
    }

}
