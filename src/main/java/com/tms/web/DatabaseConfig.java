package com.tms.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    @Bean
    @Scope("application")
    Connection connection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver"); //Без этой записи не могу получить конекшн к базе на томкате
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/tms", "postgres", "postgres");
    }
}
