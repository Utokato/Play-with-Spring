package cn.llman.beans.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 自定义注解
 * 校验 必填字段
 * <p>
 * 注解和反射 配合来校验一些运行时的数据是绝妙的
 *
 * @author marlonn
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface INotEmpty {

    String value() default "";

}
