package com.mamun.apimasterprj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;


@Configuration
public class DbConfig {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DataSource getDataSource() {
        /*return DataSourceBuilder
                .create()
                .build();*/
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }


    @Bean(name = "secSqlDataSource")
    public DataSource mySqlDataSource() {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost/testdb");
        dataSourceBuilder.username("dbuser");
        dataSourceBuilder.password("dbpass");
        return dataSourceBuilder.build();

    }


//    @Bean
//    public SessionFactory getSessionFactory() {
//        SessionFactory sessionFactory = new LocalSessionFactoryBuilder(getDataSource()).addAnnotatedClasses(User.class).buildSessionFactory();
//        return sessionFactory;
//    }
//
//    @Bean
//    public HibernateTemplate getHibernateTemplate() {
//        return new HibernateTemplate(getSessionFactory());
//    }
//
//    @Bean
//    public HibernateTransactionManager getHibernateTransManager() {
//        return new HibernateTransactionManager(getSessionFactory());
//    }


}
