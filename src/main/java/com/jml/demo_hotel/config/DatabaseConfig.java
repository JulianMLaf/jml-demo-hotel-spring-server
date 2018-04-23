package com.jml.demo_hotel.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.jml.demo_hotel.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}
