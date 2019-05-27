package cn.com.citydo.eagle.eaglespringcloud.collector;

import cn.com.citydo.eagle.eaglespringcloud.entity.EagleEyeEntity;
import cn.com.citydo.eagle.eaglespringcloud.util.UUIDUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Caorenpeng
 * 2019/5/23
 */
@Aspect
@Component
public class EagleAspect {
    final EagleEyeEntity entity = new EagleEyeEntity();
    private final static String SPANID = "spanId";
    private final static String TRACEID = "traceId";
    private final static String STARTTIME = "startTime";
    private final static String RABBITMQ = "rabbitmq";
    @Autowired
    private RabbitMqStorageSender sender;
    @Value("${spring.application.name}")
    String serviceName;

    @Value("${eagle.collector.type}")
    String type;

    @Pointcut(value = "execution(public * *.*.*.*.*.controller.*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void beforeRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String traceId = request.getHeader(TRACEID);
        String parentId = request.getHeader(SPANID);
        String spanId = UUIDUtil.getUUID();
        request.setAttribute(SPANID, spanId);
        request.setAttribute(STARTTIME, System.currentTimeMillis());
        entity.setParentId(parentId);
        entity.setSpanId(spanId);
        entity.setTraceId(traceId);
        entity.setServiceName(serviceName);
    }


    @AfterReturning(pointcut = "pointCut()", returning = "res")
    public void afterReturning(JoinPoint joinPoint, Object res) {
        EagleAcceptWrapper.accept(entity, false, joinPoint, res, null);
        if(null==type)
            sender.sendServiceMessage(entity);
        else if(RABBITMQ.equals(type))
            sender.sendServiceMessage(entity);
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        EagleAcceptWrapper.accept(entity, true, joinPoint, null, ex);
        if(null==type)
            sender.sendServiceMessage(entity);
        else if(RABBITMQ.equals(type))
            sender.sendServiceMessage(entity);
    }
}
