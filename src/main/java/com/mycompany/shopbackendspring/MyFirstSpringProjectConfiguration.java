/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Nergal
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.mycompany.shopbackendspring.data.repository"})
@EntityScan(basePackages = "com.mycompany.shopbackendspring.data.entity")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.mycompany.shopbackendspring.webservice, "
        + "com.mycompany.shopbackendspring.webapplication, "
        + "com.mycompany.shopbackendspring.service")

public class MyFirstSpringProjectConfiguration {

    private static String PROP_DB_DRIVER_CLASS = "spring.datasource.driver-class-name";
    private static String PROP_DB_URL = "spring.datasource.url";
    private static String PROP_DB_USER = "spring.datasource.username";
    private static String PROP_DB_PASS = "spring.datasource.password";

    private static final String PROP_HIBERNATE_DIALECT = "spring.jpa.database-platform";
    private static final String PROP_ENTITYMANAGER_GENERATE = "spring.jpa.generate-ddl";
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "spring.jpa.hibernate.ddl-auto";

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(PROP_DB_DRIVER_CLASS));
        dataSource.setUrl(env.getProperty(PROP_DB_URL));
        dataSource.setUsername(env.getProperty(PROP_DB_USER));
        dataSource.setPassword(env.getProperty(PROP_DB_PASS));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.mycompany.shopbackendspring.data.*");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getHibernateProperties());

        return em;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(PROP_HIBERNATE_DIALECT, env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
        properties.put(PROP_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));
        properties.put(PROP_ENTITYMANAGER_GENERATE, env.getRequiredProperty(PROP_ENTITYMANAGER_GENERATE));

        return properties;
    }
}
