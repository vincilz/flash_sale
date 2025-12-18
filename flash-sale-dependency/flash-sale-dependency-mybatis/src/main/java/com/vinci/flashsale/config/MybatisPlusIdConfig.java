package com.vinci.flashsale.config;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/18
 */
@Configuration
public class MybatisPlusIdConfig {

    @Value("${info.work-id:1}")
    private long workId;

    @Value("${info.data-center-id:1}")
    private long dataCenterId;

    @Bean
    public IdentifierGenerator idGenerator() {
        return new DefaultIdentifierGenerator(workId, dataCenterId);
    }

    @PostConstruct
    public void check() {
        Assert.isTrue(workId >= 0 && workId <= 31, "workId must be 0~31");
        Assert.isTrue(dataCenterId >= 0 && dataCenterId <= 31, "dataCenterId must be 0~31");
    }

}
