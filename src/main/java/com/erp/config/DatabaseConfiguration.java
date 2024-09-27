package com.erp.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/database.properties")
public class DatabaseConfiguration {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    // database.properties 파일에서 spring.datasource.hikari 로 시작하는 설정 값들을 가져와 Hikari 설정 객체를 반환
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() throws Exception {
        // HikariDataSource를 사용하기 위해 Hikari 설정 객체를 생성자로 넣은 HikariDataSource 객체 반환
        DataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }

    /**
     * <pre>
     * SqlSessionFactory 빈 생성
     * - mybatis와 데이터베이스 사이의 세션을 관리
     * - SQL 실행이나 트랜잭션 관리에 필요한 SqlSession 객체를 생성하는데 사용
     * </pre>
     *
     * @param dataSource 데이터베이스 연결을 위한 DataSource 객체
     * @return SqlSessionFactoryBean 객체
     * @throws Exception 예외 처리
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource); // 데이터 소스 설정

        // MyBatis 설정 파일 위치 지정
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("mybatis/mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.erp");
        return sqlSessionFactoryBean.getObject(); // SqlSessionFactory 반환
    }

    /**
     * <pre>
     * SqlSessionTemplate 빈 생성
     * - mybatis의 SqlSession 구현 및 스프링 트랜잭션 설정과의 통합을 용이하게 함
     * - SqlSession의 스레드 안전한 공유 인스턴스를 제공하여, SQL 작업 실행 및 트랜잭션 관리를 단순화
     * </pre>
     *
     * @param sqlSessionFactory SqlSessionFactory 객체
     * @return SqlSessionTemplate 객체
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

}
