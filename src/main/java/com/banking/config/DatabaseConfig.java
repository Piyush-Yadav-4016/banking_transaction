
package com.banking.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConfig {

    private static final HikariDataSource dataSource;

    static {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(
            "jdbc:postgresql://localhost:5432/banking_transaction"
        );

        config.setUsername("postgres");

        config.setPassword("123456789");

        // Connection Pool Configuration
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);

        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);

        config.setPoolName("BankingHikariPool");

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

    public static void closeDataSource() {

        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
