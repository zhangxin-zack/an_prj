package com.scorer.client._DBController;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@MapperScan(basePackages = {"com.scorer.client.dao.mysql_dao1"}, sqlSessionFactoryRef = "firstSessionFactory")
public class FirstDataSourceConfig {

    @Value("${spring.is-net-server.secure}")
    private boolean isServer;

    private static final String MAPPER_LOCATION1 = "/mybatis/mysql_mapper1/*.xml";
//    private static final String MAPPER_LOCATION2 = "/mybatis/mysql_mapper2/*.xml";
    private static final String CONFIG_LOCATION = "/mybatis/mybatis-config.xml";
    @Value("${spring.first-datasource.url}") //第一步中配置文件中的数据库配置信息
    private String dbUrl;
    @Value("${spring.first-datasource.username}")//同理为配置文件中信息
    private String dbUser;
    @Value("${spring.first-datasource.password}")//同理为配置文件信息
    private String dbPassword;
    @Value("${spring.first-datasource.driver-class-name}")
    private String driverClass;
    @Value("${spring.first-datasource.max-active}")
    private int maxActive;
    @Value("${spring.first-datasource.initial-size}")
    private int initialSize;
    @Value("${spring.first-datasource.max-wait}")
    private int maxWait;
    @Value("${spring.first-datasource.min-idle}")
    private int minIdle;
    @Value("${spring.first-datasource.max-idle}")
    private int maxIdle;
    @Value("${spring.first-datasource.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.first-datasource.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.first-datasource.test-while-idle}")
    private boolean testWhileIdle;
    @Value("${spring.first-datasource.test-on-connect}")
    private boolean testOnConnect;
    @Value("${spring.first-datasource.test-on-borrow}")
    private boolean testOnBorrow;
    @Value("${spring.first-datasource.test-on-return}")
    private boolean testOnReturn;

    @Bean(name = "firstDatasource")
    @Primary
    public DataSource firstDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setMaxActive(maxActive);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxWait(maxWait);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnConnect(testOnConnect);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        return dataSource;
    }

    @Bean(name = "firstTransactionManager")
    @Primary
    public DataSourceTransactionManager firstTransactionManager(@Qualifier("firstDatasource") DataSource adsFirstDataSource) {
        return new DataSourceTransactionManager(firstDataSource());
    }

    @Bean(name = "firstSessionFactory")
    @Primary
    public SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstDatasource") DataSource adsFirstDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(adsFirstDataSource);
        sessionFactory.setConfigLocation(new ClassPathResource(CONFIG_LOCATION));
        Resource[] resources1 = new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION1);
//        Resource[] resources2 = new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION2);
        List<Resource> resourcesList = new ArrayList<>();
        resourcesList.addAll(Arrays.asList(resources1));
//        resourcesList.addAll(Arrays.asList(resources2));
        Resource[] resources = new Resource[resourcesList.size()];
        resourcesList.toArray(resources);
        sessionFactory.setMapperLocations(resources);
        return sessionFactory.getObject();
    }


}