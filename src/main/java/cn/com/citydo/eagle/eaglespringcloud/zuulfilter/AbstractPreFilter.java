package cn.com.citydo.eagle.eaglespringcloud.zuulfilter;

import cn.com.citydo.eagle.eaglespringcloud.util.UUIDUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Caorenpeng
 * 2019/5/22
 */
public class AbstractPreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String traceId = UUIDUtil.getUUID();
        request.setAttribute("gatewayStartTime", System.currentTimeMillis());
        request.setAttribute("traceId", traceId);
        request.setAttribute("spanId", traceId);
        ctx.addZuulRequestHeader("traceId", traceId);
        ctx.addZuulRequestHeader("spanId", traceId);
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        return null;
    }
}
