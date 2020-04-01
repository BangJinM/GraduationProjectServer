package common.task;

import java.lang.reflect.Method;

public class MegExecutor {

	/** logic handler method */
	private Method method;
	/** arguments passed to method */
	private Class<?>[] params;
	/** logic controller  */
	private Object handler;

	public static MegExecutor valueOf(Method method, Class<?>[] params, Object handler) {
		MegExecutor executor = new MegExecutor();
		executor.method = method;
		executor.params = params;
		executor.handler = handler;

		return executor;
	}

	public Method getMethod() {
		return method;
	}

	public Class<?>[] getParams() {
		return params;
	}

	public Object getHandler() {
		return handler;
	}

}
