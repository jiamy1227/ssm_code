package com.jiamy.aspect;

import com.jiamy.annotation.MetricTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author jiamy
 * @Description :
 * @Create on : 2020/8/26 11:10
 **/

@Component
@Aspect
public class MetricAspect {

    @Around("@annotation(metricTime)")
    public Object doMetric(ProceedingJoinPoint joinPoint, MetricTime metricTime) throws Throwable {

        String name = metricTime.value();
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long t = System.currentTimeMillis() - start;
            // 写入日志或发送至JMX:
            System.err.println("[Metrics] " + name + ": " + t + "ms");
        }
    }
}
