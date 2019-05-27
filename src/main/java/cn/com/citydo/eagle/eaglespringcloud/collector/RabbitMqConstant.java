package cn.com.citydo.eagle.eaglespringcloud.collector;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>rabbitMq 队列配置</h1>
 * Created by Caorenpeng
 * 2019/5/23
 */
@Configuration
@ConfigurationProperties("spring.rabbitmq")
public class RabbitMqConstant {
    private int port;

    private String username;

    private String password;

    private String host;

    private String queue;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }
}
