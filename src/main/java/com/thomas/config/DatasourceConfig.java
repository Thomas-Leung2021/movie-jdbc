package com.thomas.config;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatasourceConfig {
    
    @Bean
    @Primary // set parimary as we can have multiple datasources
    @ConfigurationProperties("spring.datasource") // from application.properties
    public HikariDataSource hikriDataSource() {
        return DataSourceBuilder.create()
            .type(HikariDataSource.class)
            .build();
    }

    // This passes the datasource to the JdbcTemplate, so if we have multiple
    // datasources, we can use different templates
    @Bean
    public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource) {
        return new JdbcTemplate(hikariDataSource);
    }
}
