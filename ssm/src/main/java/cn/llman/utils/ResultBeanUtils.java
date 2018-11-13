package cn.llman.utils;

import cn.llman.beans.vo.ResultBean;

/**
 * @author
 * @date 2018/11/13
 */
public class ResultBeanUtils {

    /**
     * 成功时，根据对象信息返回ResultBean
     *
     * @param object
     * @return
     */
    public static ResultBean success(Object object) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(0);
        resultBean.setMsg("成功");
        resultBean.setData(object);
        return resultBean;
    }

    /**
     * 成功时，返回ResultBean
     *
     * @return
     */
    public static ResultBean success() {
        return success(null);
    }

    /**
     * 错误时，根据错误信息返回ResultVO
     *
     * @param code
     * @param msg
     * @return
     */
    public static ResultBean error(Integer code, String msg) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(code);
        resultBean.setMsg(msg);
        return resultBean;
    }

}
