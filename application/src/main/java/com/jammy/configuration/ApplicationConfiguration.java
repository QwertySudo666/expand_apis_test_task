package com.jammy.configuration;

import com.jammy.business.adapter.ProductRecordRepositoryAdapter;
import com.jammy.business.adapter.UserRepositoryAdapter;
import com.jammy.business.facade.ProductFacade;
import com.jammy.business.facade.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfiguration {
    @Bean
    UserFacade userFacade(UserRepositoryAdapter userRepositoryAdapter) {
        return new UserFacade(userRepositoryAdapter);
    }

    @Bean
    ProductFacade productFacade(ProductRecordRepositoryAdapter productRecordRepositoryAdapter) {
        return new ProductFacade(productRecordRepositoryAdapter);
    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
