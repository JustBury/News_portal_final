package by.bury.main.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration // Configuration class designation
@EnableWebMvc // Configuration java-class MVC designation
@ComponentScan(basePackages="by.bury.main") // XML <context:component-scan base-package="com.zaurtregulov.spring.mvc_hibernate_aop" />
@PropertySource("classpath:persistence-mysql.properties")
@EnableTransactionManagement // XML <tx:annotation-driven transaction-manager="transactionManager" />
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env;

	// set up a logger for diagnostics

	private Logger logger = Logger.getLogger(getClass().getName());

	// define a bean for ViewResolver

	// XML variant viewResolver()
//	<bean
//	class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//        <property name="prefix" value="/WEB-INF/view/" />
//        <property name="suffix" value=".jsp" />
//    </bean>

	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	// define a bean for our security dataSource()

	// XML varian dataSource()
//	  <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
//	destroy-method="close">
//        <property name="driverClass" value="com.mysql.cj.jdbc.Driver" />
//        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/my_db?useSSL=false&amp;serverTimezone=UTC" />
//        <property name="user" value="bestuser" />
//        <property name="password" value="bestuser" />
//    </bean>
	
	@Bean
	public DataSource dataSource() {

		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
			securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			securityDataSource.setUser(env.getProperty("jdbc.user"));
			securityDataSource.setPassword(env.getProperty("jdbc.password"));
		}catch (PropertyVetoException e){
			e.printStackTrace();
		}

		// log the connection props
		// for sanity's sake, log this info
		// just to make sure we are REALLY reading data from properties file
		System.out.println(">>> jdbc.url=" + env.getProperty("jdbc.url"));
		System.out.println(">>> jdbc.user=" + env.getProperty("jdbc.user"));


		logger.info(">>> jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info(">>> jdbc.user=" + env.getProperty("jdbc.user"));

		securityDataSource.setInitialPoolSize(getIntPropetry("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntPropetry("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntPropetry("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntPropetry("connection.pool.maxIdleTime"));
		return securityDataSource;
	}


	// need a helper method
	// read environment property and convert to int

	private int getIntPropetry(String propName){
		String provVal = env.getProperty(propName);
		 int intPropVal = Integer.parseInt(provVal);
		 return intPropVal;
	}

	// XML variant sessionFactory() and getHibernateProperties()
//	 <bean id="sessionFactory"
//	class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
//        <property name="dataSource" ref="dataSource" />
//        <property name="packagesToScan" value="com.zaurtregulov.spring.mvc_hibernate_aop.entity" />
//        <property name="hibernateProperties">
//            <props>
//                <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
//                <prop key="hibernate.show_sql">true</prop>
//            </props>
//        </property>
//    </bean>

	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource());
		localSessionFactoryBean.setPackagesToScan("by.bury.main.entity");
		localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
		try {
			localSessionFactoryBean.afterPropertiesSet();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return localSessionFactoryBean.getObject();



	}

	@Bean
	private static Properties getHibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.generate_statistics", false);
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.put("hibernate.use_sql_comments", false);
		return hibernateProperties;
	}

// XML variant
//	<bean id="transactionManager"
//	class="org.springframework.orm.hibernate5.HibernateTransactionManager">
//        <property name="sessionFactory" ref="sessionFactory"/>
//    </bean>
	@Bean
	public PlatformTransactionManager dbTransactionManager() {
		JpaTransactionManager transactionManager
				= new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(
				sessionFactory());
		return transactionManager;
	}
//  XML variant addResourceHandlers(ResourceHandlerRegistry registry)
//	<!-- Add support for reading web resources: css, images, js, etc ... -->
//    <mvc:resources location="/resources/" mapping="/resources/**"/>

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
















