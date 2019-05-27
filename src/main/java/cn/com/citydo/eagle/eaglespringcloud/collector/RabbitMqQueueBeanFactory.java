package cn.com.citydo.eagle.eaglespringcloud.collector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>消息队列 队列配置</h1>
 * Created by Caorenpeng
 * 2019/5/23
 */
@Configuration
public class RabbitMqQueueBeanFactory {
    @Autowired
    private RabbitMqConstant constant;
    private final static Logger logger = LoggerFactory.getLogger(RabbitMqQueueBeanFactory.class);

    @Bean
    public Queue eagleEyeQueue() {
        logger.info("***************************************");
        logger.info("*****************Eagle*****************");
        logger.info("***************************************");
        logger.info(" :: Eagle ::        (v1.0.1.RELEASE)");
        return new Queue(constant.getQueue(), true);
    }
}
