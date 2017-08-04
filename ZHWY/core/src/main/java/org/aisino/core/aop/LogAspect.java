package org.aisino.core.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 日志记录 切面
 */
@Configuration
@Aspect
public class LogAspect {

	@Autowired
	private HttpServletRequest request;

	@Pointcut("execution(* org.aisino.aos.*.controller.*.*(..))")
	private void anyMethod() {
	}// 定义一个切入点

	// 前置通知
	// 在切点方法集合执行前，执行前置通知
	@Before(value = "anyMethod()")
	public void doBefore(JoinPoint jp) {
		try {
			if (request != null) {
				// 调用对象名
				String objname = jp.getTarget().getClass().toString();
				// 调用方法名
				String method = jp.getSignature().getName();
				// 调用对象方法 参数
				Object[] args = jp.getArgs();
				System.out.println("调用对象:" + objname + ",对象方法:" + method
						+ ",参数:" + Arrays.toString(args));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 后置通知
	 * 
	 * @param jp
	 * @param result
	 */
	/*
	 * @AfterReturning(value = "anyMethod()", argNames = "rtv", returning =
	 * "rtv") public void doAfter(JoinPoint jp, String result) {
	 * System.out.println("==========进入after advice=========== \n");
	 * System.out.println("切入点方法执行完了 \n"); System.out.print(jp.getArgs()[0] +
	 * "在"); System.out.print(jp.getTarget().getClass() + "对象上被");
	 * System.out.print(jp.getSignature().getName() + "方法删除了");
	 * System.out.print("只留下：" + result + "\n\n"); }
	 */

	/**
	 * 环绕通知（环绕通知的方法中一定要有ProceedingJoinPoint 参数,与 Filter中的 doFilter方法类似）
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	/*
	 * @Around("anyMethod()") public Object doAround(ProceedingJoinPoint pjp)
	 * throws Throwable {
	 * System.out.println("===========进入around环绕方法！=========== \n"); //
	 * HttpSession session = ServletActionContext.getRequest().getSession(); //
	 * 调用目标方法之前执行的动作 System.out.println("调用方法之前: 执行！\n");
	 * 
	 * // 调用方法的参数 Object[] args = pjp.getArgs(); // 调用的方法名 String method =
	 * pjp.getSignature().getName(); //
	 * 获取目标对象(形如：com.action.admin.LoginAction@1a2467a) Object target =
	 * pjp.getTarget(); // 获取目标对象的类名(形如：com.action.admin.LoginAction) String
	 * targetName = pjp.getTarget().getClass().getName();
	 * 
	 * // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行 Object result = pjp.proceed();//
	 * result的值就是被拦截方法的返回值
	 * 
	 * System.out.println("输出：" + args[0] + ";" + method + ";" + target + ";" +
	 * result + "\n"); System.out.println("调用方法结束：之后执行！\n"); return result; }
	 */

	/**
	 * 异常通知
	 * 
	 * @param jp
	 * @param e
	 */
	/*
	 * @AfterThrowing(value = "anyMethod()", throwing = "e") public void
	 * doThrow(JoinPoint jp, Throwable e) {
	 * System.out.println(jp.getTarget().getClass().toString() + "." +
	 * jp.getSignature().getName() + "异常:" + e.getMessage()); }
	 */
}
