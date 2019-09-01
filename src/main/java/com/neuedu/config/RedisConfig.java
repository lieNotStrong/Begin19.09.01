package com.neuedu.config;


import com.neuedu.common.RedisPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {



    @Value("${redis.max.total}")
    private Integer maxTotle;
    @Value("${redis.max.idel}")
    private Integer maxIdel;
    @Value("${redis.min.idel}")
    private Integer minIdel;
    @Value("${redis.test.borrow}")
    private boolean testBorrow;
    @Value("${redis.test.return}")
    private boolean testReturn;
    @Value("${redis.timeout}")
    private Integer timeOut;
    @Value("${redis.ip}")
    private String ip;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.password}")
    private String password;


    @Bean
    public RedisPool redisPool(){


        return new RedisPool(maxTotle, maxIdel, minIdel, testBorrow, testReturn, timeOut, ip, port, password);
    }


    public Integer getMaxTotle() {
        return maxTotle;
    }

    public void setMaxTotle(Integer maxTotle) {
        this.maxTotle = maxTotle;
    }

    public Integer getMaxIdel() {
        return maxIdel;
    }

    public void setMaxIdel(Integer maxIdel) {
        this.maxIdel = maxIdel;
    }

    public Integer getMinIdel() {
        return minIdel;
    }

    public void setMinIdel(Integer minIdel) {
        this.minIdel = minIdel;
    }

    public boolean isTestBorrow() {
        return testBorrow;
    }

    public void setTestBorrow(boolean testBorrow) {
        this.testBorrow = testBorrow;
    }

    public boolean isTestReturn() {
        return testReturn;
    }

    public void setTestReturn(boolean testReturn) {
        this.testReturn = testReturn;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
