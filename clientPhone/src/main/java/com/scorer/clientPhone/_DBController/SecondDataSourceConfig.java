package com.scorer.clientPhone._DBController;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Configuration
@MapperScan(basePackages = {"com.scorer.client.dao.oracle_dao1", "com.scorer.client.dao.oracle_dao2"}, sqlSessionFactoryRef = "secondSessionFactory")
public class SecondDataSourceConfig {
    @Value("${spring.is-net-server.secure}")
    private boolean isServer;
    private static final String MAPPER_LOCATION1 = "/mybatis/oracle_mapper1/*.xml";
    private static final String MAPPER_LOCATION2 = "/mybatis_jp/oracle_mapper1/*.xml";
    private static final String CONFIG_LOCATION = "/mybatis/mybatis-config.xml";
    @Value("${spring.second-datasource.url}") //第一步中配置文件中的数据库配置信息
    private String dbUrl;
    @Value("${spring.second-datasource.username}")//同理为配置文件中信息
    private String dbUser;
    @Value("${spring.second-datasource.password}")//同理为配置文件信息
    private String dbPassword;
    @Value("${spring.second-datasource.driver-class-name}")
    private String driverClass;
    @Value("${spring.second-datasource.max-active}")
    private int maxActive;
    @Value("${spring.second-datasource.initial-size}")
    private int initialSize;
    @Value("${spring.second-datasource.max-wait}")
    private int maxWait;
    @Value("${spring.second-datasource.min-idle}")
    private int minIdle;
    @Value("${spring.second-datasource.max-idle}")
    private int maxIdle;
    @Value("${spring.second-datasource.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.second-datasource.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.second-datasource.test-while-idle}")
    private boolean testWhileIdle;
    @Value("${spring.second-datasource.test-on-connect}")
    private boolean testOnConnect;
    @Value("${spring.second-datasource.test-on-borrow}")
    private boolean testOnBorrow;
    @Value("${spring.second-datasource.test-on-return}")
    private boolean testOnReturn;

    @Bean(name = "secondDatasource")
    public DataSource secondDataSource() {
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

    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager secondTransactionManager(@Qualifier("secondDatasource") DataSource adsSecondDataSource) {
        return new DataSourceTransactionManager(secondDataSource());
    }

    @Bean(name = "secondSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDatasource") DataSource adsSecondDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(adsSecondDataSource);
        sessionFactory.setConfigLocation(new ClassPathResource(CONFIG_LOCATION));
        Resource[] resources1 = new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION1);
        Resource[] resources2 = new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION2);
        List<Resource> resourcesList = new ArrayList<>();
        resourcesList.addAll(Arrays.asList(resources1));
        resourcesList.addAll(Arrays.asList(resources2));
        Resource[] resources = new Resource[resourcesList.size()];
        resourcesList.toArray(resources);
        sessionFactory.setMapperLocations(resources);
        return sessionFactory.getObject();
    }
}