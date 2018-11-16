package cn.llman.common.aop;

import cn.llman.beans.vo.ResultBean;
import cn.llman.common.exception.CheckException;
import cn.llman.common.exception.UnLoginException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2018/11/15
 */
@Component
@Aspect
@Slf4j
public class BookControllerAOP {

    @Pointcut("execution(public cn.llman.beans.vo.ResultBean *(..))")
    public void executeMethod() {

    }

    @Before("executeMethod()")
    public void beforeExecuteMethod() {
    }


    @After("executeMethod()")
    public void afterExecuteMethod() {
    }

    @Around("executeMethod()")
    public Object aroundExecuteMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        ResultBean<?> result;

        try {
            result = (ResultBean<?>) pjp.proceed();

            // 如果需要打印入参，可以从这里取出打印
            // Object[] args = pjp.getArgs();

            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("[Book Project **] - [{}] use time: {}", pjp.getSignature(), elapsedTime + " ms.");
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();

        // 对各种异常进行校验和处理
        // 通过对不同异常的判断来实现返回不同的result
        if (e instanceof CheckException || e instanceof IllegalArgumentException) {
            result.setMsg(e.getLocalizedMessage());
            result.setCode(ResultBean.CHECK_FAIL);
        } else if (e instanceof UnLoginException) {
            result.setMsg("用户未登录");
            result.setCode(ResultBean.NO_LOGIN);
        } else {
            log.error(pjp.getSignature() + " error ", e);
            // TODO 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMsg(e.toString());
            result.setCode(ResultBean.UNKNOWN_EXCEPTION);
        }
        return result;
    }

}
