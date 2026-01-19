package com.example.step11.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Slf4j
class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.h2-embedded")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(HikariConfig hikariConfig) {
        log.info("HikariConfig.driverClassName = {}", hikariConfig.getDriverClassName());
        log.info("HikariConfig.jdbcUrl = {}", hikariConfig.getJdbcUrl());

        return new HikariDataSource(hikariConfig);
    }
}