package com.example.demo.StudentLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class StudentLog {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentLog.class);


	@Around("execution(* com.example.demo.StudentService.StudentService.*(..)))")
	public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

		final StopWatch stopWatch = new StopWatch();

		stopWatch.start();
		LOGGER.info(
				"Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
		
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();
		LOGGER.info(
				"Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");

		return result;
	}
}
