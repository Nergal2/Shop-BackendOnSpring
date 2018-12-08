/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shopbackendspring;

import javax.sql.DataSource;
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

/**
 *
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
    
    @Autowired
    private Environment env;
    
    @Bean
    public DataSource dataSource(){
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
   //       em.setJpaProperties(additionalProperties());

          return em;
    }
}