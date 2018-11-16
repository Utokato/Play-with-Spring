package cn.llman.common.utils;

		import java.util.Collection;

/**
 * 不要直接使用框架提供的工具类
 * <p>
 * 需要自己封装一层，目的是解耦
 * <p>
 * 后期如果需要修改可以直接修改自己的工具类
 *
 * @author
 * @date 2018/11/15
 */
public class StringUtils {

	public static boolean isEmpty(CharSequence cs) {
		return org.springframework.util.StringUtils.isEmpty(cs);
	}

	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.size() == 0;
	}

}
