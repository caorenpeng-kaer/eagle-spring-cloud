package cn.com.citydo.eagle.eaglespringcloud.collector;

import cn.com.citydo.eagle.eaglespringcloud.entity.EagleEyeEntity;
import cn.com.citydo.eagle.eaglespringcloud.exceptions.CommonException;
import cn.com.citydo.eagle.eaglespringcloud.util.SysResult;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by Caorenpeng
 * 2019/5/24
 */
public class EagleAcceptWrapper {
    private final static Integer SUCCESS = 200;

    private final static Integer FAIL = 500;

    public static EagleEyeEntity accept(EagleEyeEntity entity, boolean isThrowing, JoinPoint joinPoint, Object res, Throwable ex) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        long executorTime = System.currentTimeMillis() - (long) request.getAttribute("startTime");
        entity.setExecuteTime(executorTime);
        entity.setUrl(request.getRequestURI());
        entity.setMethod(request.getMethod());
        entity.setParamters(Arrays.toString(joinPoint.getArgs()));
        String clazz = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        entity.setClazz(clazz);
        //如果异常
        if (isThrowing) {
            if (ex instanceof CommonException) {
                CommonException e = (CommonException) ex;
                entity.setStatus(e.getCode());
                entity.setErrorMessage(e.getMessage());
            } else if (ex instanceof Exception) {
                Exception e = (Exception) ex;
                entity.setStatus(FAIL);
                entity.setErrorMessage(e.getMessage());
            }
        } else {
            if (res instanceof SysResult) {
                SysResult result = (SysResult) res;
                entity.setResponseResult(result.toString());
                entity.setStatus(result.getCode());
            } else {
                entity.setResponseResult(res.toString());
                entity.setStatus(SUCCESS);
            }
        }
        return entity;
    }
}
