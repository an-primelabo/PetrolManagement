package ah.petrolmanagement.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import ah.petrolmanagement.utils.LogUtil;

@Configuration
@MapperScan("ah.petrolmanagement.persistence")
@PropertySource(value = { "classpath:jdbc.properties" })
public class DataConfig {
	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		LogUtil.startMethod(this.getClass().getSimpleName(), "dataSource");

		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(org.postgresql.Driver.class);
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		LogUtil.startMethod(this.getClass().getSimpleName(),
				"transactionManager");

		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(),
				"sqlSessionFactory");

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory
				.setMapperLocations(new PathMatchingResourcePatternResolver()
						.getResources("classpath:ah/petrolmanagement/mapper/*.xml"));
		return sessionFactory;
	}
}