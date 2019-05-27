package cn.com.citydo.eagle.eaglespringcloud.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>服务链路之间下游传参 拦截器</h1>
 * Created by Caorenpeng
 * 2019/5/23
 */
public class FeignBasicRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String traceId = request.getHeader("traceId");
        String spanId = (String) request.getAttribute("spanId");
        requestTemplate.header("traceId", traceId);
        requestTemplate.header("spanId", spanId);
    }
}
