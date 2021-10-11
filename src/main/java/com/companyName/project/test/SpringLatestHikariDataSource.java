package com.companyName.project.test;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class SpringLatestHikariDataSource {

    private final HikariDataSource dataSource;

    public SpringLatestHikariDataSource(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void testConn(){

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
