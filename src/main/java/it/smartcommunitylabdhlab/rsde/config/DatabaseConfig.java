package it.smartcommunitylabdhlab.rsde.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import it.smartcommunitylabdhlab.rsde.persistence.IsolationSupportHibernateJpaDialect;

/**
 * Database config is @1, we need dataSources to bootstrap
 *
 * @author raman
 *
 */
@Configuration
@Order(1)
@EnableTransactionManagement
@EntityScan(basePackages = { "it.smartcommunitylabdhlab.rsde.persistence" })
@EnableJpaRepositories(basePackages = {
	"it.smartcommunitylabdhlab.rsde.persistence" }, queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
@EnableJpaAuditing
public class DatabaseConfig {

    @Bean(name = "jdbcDataSource")
    public HikariDataSource jdbcDataSource(@Qualifier("jdbcProperties") JdbcProperties properties) {
	HikariConfig config = buildDataSourceConfig(properties);
	config.setPoolName("jdbcConnectionPool");

	return new HikariDataSource(config);
    }

    @Primary
    @Bean(name = "jpaDataSource")
    public HikariDataSource jpaDataSource(@Qualifier("jdbcProperties") JdbcProperties properties) {
	HikariConfig config = buildDataSourceConfig(properties);
	config.setPoolName("jpaConnectionPool");

	return new HikariDataSource(config);
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(
	    @Qualifier("jdbcProperties") JdbcProperties properties, @Qualifier("jpaDataSource") DataSource dataSource) {
	LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
	bean.setPersistenceUnitName("rsde");
	bean.setDataSource(dataSource);

	HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	adapter.setDatabasePlatform(properties.getDialect());
	if (properties.isShowSql()) {
	    adapter.setShowSql(true);
	}
	adapter.setGenerateDdl(true);
	bean.setJpaVendorAdapter(adapter);

	bean.setJpaDialect(new IsolationSupportHibernateJpaDialect());

	Properties props = new Properties();
	props.setProperty("hibernate.hbm2ddl.auto", "update");
	bean.setJpaProperties(props);

	bean.setPackagesToScan("it.smartcommunitylabdhlab.rsde.persistence");
	
	return bean;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory) {
	JpaTransactionManager bean = new JpaTransactionManager();
	bean.setEntityManagerFactory(entityManagerFactory);
	return bean;
    }

    private HikariConfig buildDataSourceConfig(JdbcProperties properties) {
	HikariConfig config = new HikariConfig();
	config.setDriverClassName(properties.getDriver());
	config.setJdbcUrl(properties.getUrl());
	config.setUsername(properties.getUser());
	config.setPassword(properties.getPassword());

	config.setMaximumPoolSize(properties.getMaxPoolSize());
	config.setMinimumIdle(properties.getMinPoolSize());
	config.setIdleTimeout(properties.getIdleTimeout());
	config.setKeepaliveTime(properties.getKeepAliveTimeout());
	config.setConnectionTimeout(properties.getConnectionTimeout());

	if (properties.getDataSourceProperties() != null) {
	    properties.getDataSourceProperties().entrySet().forEach(e -> config.addDataSourceProperty(e.getKey(), e));
	}

	return config;
    }
}
