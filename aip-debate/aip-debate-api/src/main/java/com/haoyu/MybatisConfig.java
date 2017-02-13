package com.haoyu;



import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.ClassUtils;

import com.alibaba.druid.pool.DruidDataSource;



/*
 * @see http://mybatis.github.io/spring/mappers.html
 */
//@Configuration
//@MapperScan(basePackages="com.haoyu.aip.debate.dao.impl.mybatis",annotationClass=Repository.class)
public class MybatisConfig {

	private static Logger log = LoggerFactory.getLogger(MybatisConfig.class);

	@Autowired
	private JdbcConnectionSettings jdbcConnectionSettings;


	@Bean
	public DataSource dataSource() {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(jdbcConnectionSettings.getDriver());
		ds.setUsername(jdbcConnectionSettings.getUsername());
		ds.setPassword(jdbcConnectionSettings.getPassword());
		ds.setUrl(jdbcConnectionSettings.getUrl());
		ds.setMaxActive(jdbcConnectionSettings.getMaxActive());
		ds.setValidationQuery(jdbcConnectionSettings.getValidationQuery());
		ds.setTestOnBorrow(jdbcConnectionSettings.getTestOnBorrow());
		ds.setTestOnReturn(jdbcConnectionSettings.getTestOnReturn());
		ds.setTestWhileIdle(jdbcConnectionSettings.getTestWhileIdle());
		ds.setTimeBetweenEvictionRunsMillis(jdbcConnectionSettings.getTimeBetweenEvictionRunsMillis());
		ds.setMinEvictableIdleTimeMillis(jdbcConnectionSettings.getMinEvictableIdleTimeMillis());
		return ds;
	}


	public Resource[] getResource( String basePackage, String pattern ) throws IOException {
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ ClassUtils.convertClassNameToResourcePath(new StandardEnvironment()
						.resolveRequiredPlaceholders(basePackage)) + "/" + pattern;
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
		return resources;
	}

	@Autowired
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		log.debug("> sqlSessionFactory");
		final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		sqlSessionFactory.setFailFast(true);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:com/haoyu/aip/debate/dao/impl/mybatis/*.xml"));
		//sqlSessionFactory.setTypeHandlersPackage("demo.springboot.configuration.mybatis.typehandler");
		return sqlSessionFactory.getObject();
	}


	@Bean
	public DataSourceTransactionManager transactionManager() {
		log.debug("> transactionManager");
		return new DataSourceTransactionManager(dataSource());
	}


	@PostConstruct
	public void postConstruct() {
		log.info("jdbc.settings={}", jdbcConnectionSettings);
	}
	
	 
}
