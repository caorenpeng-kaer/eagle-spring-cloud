package cn.com.citydo.eagle.eaglespringcloud.zuulfilter;

import cn.com.citydo.eagle.eaglespringcloud.entity.EagleEyeEntity;
import cn.com.citydo.eagle.eaglespringcloud.util.JsonBeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Caorenpeng
 * 2019/5/24
 */
public class AbstractPostFilter extends ZuulFilter {
    private final static Logger logger = LoggerFactory.getLogger(AbstractPostFilter.class);
    private final static String STARTTIME = "gatewayStartTime";
    final EagleEyeEntity entity = new EagleEyeEntity();
    final ExecutorService executor = Executors.newFixedThreadPool(50);
    @Autowired
    private AmqpTemplate template;
    @Value("${spring.application.name}")
    private String serviceName;
    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        long startTime = (long) request.getAttribute(STARTTIME);
        String traceId = (String) request.getAttribute("traceId");
        String spanId = (String) request.getAttribute("spanId");
        entity.setExecuteTime(System.currentTimeMillis() - startTime);
        entity.setTraceId(traceId);
        entity.setSpanId(spanId);
        entity.setUrl(request.getRequestURI());
        entity.setServiceName(serviceName);
        entity.setMethod(request.getMethod());
        executor.execute(() -> {
            logger.info("****************start send datas****************");
            JSONObject js = JsonBeanUtil.bean2Json(entity);
            try {
                template.convertAndSend(queue, js);
                logger.info("****************end send datas****************");
            }catch (Exception e){
                logger.error("fail to send datas:{}", e.getMessage());
            }
        });
        return null;
    }
}
