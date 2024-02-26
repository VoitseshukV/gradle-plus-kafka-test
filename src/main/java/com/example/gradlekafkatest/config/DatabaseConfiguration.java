package com.example.gradlekafkatest.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {
    @Bean
    public DataSource dataSource(@Value("${spring.datasource.host}") final String host,
                                 @Value("${spring.datasource.port}") final int port,
                                 @Value("${spring.datasource.database}") final String database,
                                 @Value("${spring.datasource.username}") final String username,
                                 @Value("${spring.datasource.password}") final String password,
                                 @Value("${spring.datasource.maximumPoolSize}") final Integer maximumPoolSize) {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(String.format("jdbc:mysql://%s:%d/%s?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true&useSSL=false", host, port, database));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxLifetime(0L);
        dataSource.setMaximumPoolSize(maximumPoolSize);

        return dataSource;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(final DataSource dataSource) {
        FluentConfiguration configuration = Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource(dataSource);
        return new Flyway(configuration);
    }
}
