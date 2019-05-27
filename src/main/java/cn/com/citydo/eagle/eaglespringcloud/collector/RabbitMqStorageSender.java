package cn.com.citydo.eagle.eaglespringcloud.collector;

import cn.com.citydo.eagle.eaglespringcloud.entity.EagleEyeEntity;
import cn.com.citydo.eagle.eaglespringcloud.util.JsonBeanUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Caorenpeng
 * 2019/5/23
 */
@Service(value = "rabbitmqsender")
public class RabbitMqStorageSender {
    private final static Logger logger = LoggerFactory.getLogger(RabbitMqStorageSender.class);
    @Autowired
    private AmqpTemplate template;
    @Autowired
    private RabbitMqConstant constant;

    @Async(value = "rabbitmqAsync")
    public void sendServiceMessage(EagleEyeEntity entity) {
        logger.info("****************start send datas****************");
        JSONObject js = JsonBeanUtil.bean2Json(entity);
        try {
            template.convertAndSend(constant.getQueue(), js);
            logger.info("****************end send datas****************");
        } catch (Exception e) {
            logger.error("fail to send datas:{}", e.getMessage());
        }
    }
}
