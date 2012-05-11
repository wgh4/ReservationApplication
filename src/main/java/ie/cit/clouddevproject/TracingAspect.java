package ie.cit.clouddevproject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class TracingAspect {
	private static Log log = LogFactory.getLog(TracingAspect.class);
	
	// [Modifiers] ReturnType [ClassType] MethodName ([Arguments]) [throws Exception]
	
	@Before("execution(* ie.cit.clouddevproject.JdbcReservationDatabase.*(..))")
	public void traceMethod(JoinPoint point) {
		String className = point.getTarget().getClass().getName();
		String methodName = point.getSignature().getName();
		log.trace("method invoked:"+className+"#"+methodName);
	}
	
// Had to disable @AfterThrowing code below as it caused a SEVERE error as follows:
// May 11, 2012 12:01:32 PM org.apache.catalina.core.StandardContext listenerStart
// SEVERE: Exception sending context initialized event to listener instance of class org.springframework.web.context.ContextLoaderListener
	
//	@AfterThrowing("execution(* ie.cit.clouddevproject.JdbcReservationDatabase.*(..))")
//	public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
	 
//	  String className = joinPoint.getTarget().getClass().getName();
//	  String methodName = joinPoint.getSignature().getName();
//	  String errorMessage = throwable.getMessage();
//	  log.error("error in method:"+className+"#"+methodName+"@"+errorMessage+"#"+joinPoint.getArgs() );
//	}
	
	
	
}
