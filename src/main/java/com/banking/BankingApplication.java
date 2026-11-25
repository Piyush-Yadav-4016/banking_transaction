
package com.banking;

import com.banking.config.DatabaseConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

public class BankingApplication {

    public static void main(String[] args) {

        try {

            HikariDataSource dataSource =
                    DatabaseConfig.getDataSource();

            try (Connection connection = dataSource.getConnection()) {

                System.out.println(
                    "========================================"
                );

                System.out.println(
                    " BANKING TRANSACTION PROCESSING SYSTEM"
                );

                System.out.println(
                    "========================================"
                );

                System.out.println("Connection successful!");

                DatabaseMetaData metadata =
                        connection.getMetaData();

                System.out.println(
                    "Database: " + connection.getCatalog()
                );

                System.out.println(
                    "Database Product: " +
                    metadata.getDatabaseProductName()
                );

                System.out.println(
                    "Database Version: " +
                    metadata.getDatabaseProductVersion()
                );

                System.out.println(
                    "JDBC Driver: " +
                    metadata.getDriverVersion()
                );

                String sql =
                    "SELECT current_database(), current_user";

                try (
                    Statement statement =
                        connection.createStatement();

                    ResultSet resultSet =
                        statement.executeQuery(sql)
                ) {

                    if (resultSet.next()) {

                        System.out.println(
                            "Current Database: " +
                            resultSet.getString(1)
                        );

                        System.out.println(
                            "Connected User: " +
                            resultSet.getString(2)
                        );
                    }
                }

                try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(
                        "SELECT NOW()")) {

                    if (resultSet.next()) {
                        System.out.println(
                            "Database Server Time: " +
                            resultSet.getTimestamp(1)
                        );
                    }
                }


                System.out.println(
                    "========================================"
                );

                System.out.println(
                    " DATABASE CONNECTION VERIFIED"
                );

                System.out.println(
                    "========================================"
                );
            }

        } catch (Exception e) {

            System.err.println(
                "Database connection failed: " +
                e.getMessage()
            );

            e.printStackTrace();

        } finally {

            DatabaseConfig.closeDataSource();
        }
    }
}
