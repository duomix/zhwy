package org.aisino.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

/**
 * 影响 controller 返回  不能使用 待解决
 * @author XZY
 * 2017-3-31-下午1:50:07
 */
@Aspect
@Component
public class ServiceMonitor {

	@Autowired
	private GaugeService gaugeService;

	@Autowired
	private CounterService counterService;

	/**
	 * 统计controller调用次数
	 * 
	 * @param joinPoint
	 */
	/*@Before("execution(* org.aisino.erp.controller.*.*(..))")
	public void countServiceInvoke(JoinPoint joinPoint) {
		counterService.increment(joinPoint.getSignature() + "");
	}*/
	
	/**
	 * 统计controller执行时间
	 * @param pjp
	 * @throws Throwable
	 */
	/*@Around("execution(* org.aisino.erp.controller.*.*(..))")
	public void latencyService(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		pjp.proceed();
		long end = System.currentTimeMillis();
		gaugeService.submit(pjp.getSignature().toString(), end - start);
	}*/

}
