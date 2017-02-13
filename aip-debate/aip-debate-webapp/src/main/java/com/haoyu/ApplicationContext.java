package com.haoyu;



import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@SpringBootApplication
public class ApplicationContext extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationContext.class);
	}
	@Autowired
	@Bean
	public DataSourceInitializer dataSourceInitializer(
			@Qualifier("dataSource") DataSource dataSource) {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("sql"));
		dataSourceInitializer.setDatabasePopulator(databasePopulator);
		dataSourceInitializer.setEnabled(false);

		return dataSourceInitializer;
	}

	@Autowired
	@Bean
	public DataSourceTransactionManager transactionManager(
			@Qualifier("dataSource") DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}
	
	@Autowired
	@Bean
	public SqlSessionFactory sqlSessionFactory(
			@Qualifier("dataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);

/*		ResourcePatternResolver resolver = ResourcePatternUtils
				.getResourcePatternResolver(new DefaultResourceLoader());*/
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// MyBatis のコンフィグレーションファイル
		bean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
		// MyBatis で使用する SQL ファイル群
		
		bean.setMapperLocations(resolver.getResources("classpath*:**/*Mapper.xml"));

		return bean.getObject();
	}

	@Primary
	@Autowired
	@Bean
	public DriverManagerDataSource dataSource() {
		// 今回の例は ORACLE、適宜変更する
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/aip?useUnicode=true&amp;charaterEncoding=utf-8");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		return dataSource;
	}
	
	public static void main(String[] args) {
		System.out.println("hello");
		SpringApplication.run(ApplicationContext.class, args); 
	}
}
