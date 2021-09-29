package com.mamun.apimasterprj.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// https://docs.oracle.com/cd/E17952_01/connector-j-5.1-en/connector-j-usagenotes-statements.html

@Service
public class DbSourceNativeSqlTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    @Qualifier("secSqlDataSource")
    DataSource dataSourceSecondary;

    //TODO

    public void  cleanupDatasource() throws Exception {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = null;
                try {
                    stmt.execute("SELECT * FROM BAS_ITEM");
                    rs = stmt.getResultSet();
//                    connection.commit(); // auto commit true for MySql
//                    System.out.println("Schema truncated...");
                } finally {
                    stmt.close();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new Exception(e);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }



}
