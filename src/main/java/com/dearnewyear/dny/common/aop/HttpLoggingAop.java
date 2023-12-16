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
        log.info("Executing: {}", joinPoint.getSignature());
        try {
            Object result = joinPoint.proceed();
            if (result instanceof ResponseEntity) {
                logResponse((ResponseEntity) result);
                return result;
            }
            log.info("Result: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Exception: ", e);
            throw e;
        }
    }

    private void logResponse(ResponseEntity responseEntity) {
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("ErrorCode: {}", responseEntity.getStatusCode());
            log.info("ErrorMessage: {}", responseEntity.getBody());
            return;
        }
        log.info("StatusCode: {}", responseEntity.getStatusCode());
        log.info("Response: {}", responseEntity.getBody());
    }
}
