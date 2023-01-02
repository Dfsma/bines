package com.dfsma.bin.userservice.config;

import jakarta.persistence.EntityManagerFactory;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EntityScan("com.dfsma.bin.userservice.entity")
@EnableJpaRepositories(entityManagerFactoryRef = "oracleEntityManagerFactory",
        transactionManagerRef = "oracleTransactionManager",
        basePackages = "com.dfsma.bin.userservice.repository")
@EnableTransactionManagement
public class DataBaseConfig {
    /*
     * The jpa props.eq
     */
    private static final Map<String, String> jpaProps = new HashMap<>();
    static {
        jpaProps.put("hibernate.hbm2ddl.auto", "none");
        jpaProps.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
    }

    /**
     * @return DataSource
     */
    @Bean(destroyMethod = "", name = "oracle-db")
    public DataSource dataSource() throws NamingException, SQLException {

        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser("bin_identities");
        dataSource.setPassword("bin_identities");
        dataSource.setURL("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setImplicitCachingEnabled(true);
        return dataSource;

    }

    /**
     *
     * Second entity manager factory.**
     *
     * @param builder    the builder*
     * @param dataSource the data source
     * @return the local container entity manager factory bean
     */
    @Bean(name = "oracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                                                             final @Qualifier("oracle-db") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.dfsma.bin.userservice.entity")
                .persistenceUnit("secondDb").properties(jpaProps).build();
    }

    /**
     *
     * Second transaction manager.**
     *
     * @param secondEntityManagerFactory the second entity manager factory
     * @return the platform transaction manager
     */
    @Bean(name = "oracleTransactionManager")
    public PlatformTransactionManager secondTransactionManager(@Qualifier("oracleEntityManagerFactory") EntityManagerFactory secondEntityManagerFactory) {
        return new JpaTransactionManager(secondEntityManagerFactory);
    }
}
