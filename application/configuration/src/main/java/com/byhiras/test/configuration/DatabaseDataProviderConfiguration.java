package com.byhiras.test.configuration;

import com.byhiras.test.core.usercase.store.BidStore;
import com.byhiras.test.dataproviders.database.BidStoreImpl;
import com.byhiras.test.dataproviders.database.ItemStoreImpl;
import com.byhiras.test.dataproviders.database.UserStoreImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseDataProviderConfiguration {

    @Bean
    public BidStore bidStore(JdbcTemplate jdbcTemplate){
        return new BidStoreImpl(jdbcTemplate);
    }

    @Bean
    public ItemStoreImpl itemStore(JdbcTemplate jdbcTemplate){
        return new ItemStoreImpl(jdbcTemplate);
    }

    @Bean
    public UserStoreImpl userStore(JdbcTemplate jdbcTemplate){
        return new UserStoreImpl(jdbcTemplate);
    }
}
