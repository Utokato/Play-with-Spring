package cn.llman.beans.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author
 * @date 2018/11/13
 */
@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 2749139545188681382L;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回信息
     */
    private T data;

}

