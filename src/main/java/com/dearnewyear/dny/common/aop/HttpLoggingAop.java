package com.dearnewyear.dny.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class HttpLoggingAop {

    @Around("execution(* com.dearnewyear.dny..controller..*.*(..))")
    public Object logHttpAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object result = joinPoint.proceed();
        if (result instanceof ResponseEntity) {
            logResponse((ResponseEntity) result);
        } else {
            log.info("{} returned: {}", methodName, result);
        }
        return result;
    }

    private void logResponse(ResponseEntity responseEntity) {
        String status = responseEntity.getStatusCode().toString();
        log.info("Response: {} - {}", status, responseEntity.getBody());
    }
}