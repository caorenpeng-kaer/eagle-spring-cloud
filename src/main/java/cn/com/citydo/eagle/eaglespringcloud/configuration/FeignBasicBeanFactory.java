package cn.com.citydo.eagle.eaglespringcloud.configuration;

import cn.com.citydo.eagle.eaglespringcloud.interceptor.FeignBasicRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Caorenpeng
 * 2019/5/23
 */
@Configuration
public class FeignBasicBeanFactory {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignBasicRequestInterceptor();
    }
}
