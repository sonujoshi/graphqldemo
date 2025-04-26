package com.example.graphqldemo.config;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

import java.time.Duration;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class DatabaseConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(DRIVER, "mysql") // Note: The driver identifier might be different for asyncer
                .option(HOST, "localhost")
                .option(PORT, 3306)
                .option(USER, "root")
                .option(PASSWORD, "Sonu@1989")
                .option(DATABASE, "graphqldemo")
                .option(SSL, false)
                .build();

        ConnectionFactory connectionFactory = ConnectionFactories.get(options);

        ConnectionPoolConfiguration configuration = ConnectionPoolConfiguration.builder(connectionFactory)
                .maxIdleTime(Duration.ofMinutes(30))
                .maxLifeTime(Duration.ofHours(1))
                .maxAcquireTime(Duration.ofSeconds(30))
                .maxCreateConnectionTime(Duration.ofSeconds(30))
                .initialSize(5)
                .maxSize(20)
                .validationQuery("SELECT 1")
                .build();

        return new ConnectionPool(configuration);
    }
    @Bean
    public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.create(connectionFactory);
    }
}
