package com.kongkheang.kmb.api.config;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Spring Data JPA datasource configuration
 * @author Mr.SAY SEAK LENG
 *
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfiguration extends HikariConfig {

	@Primary
    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws SQLException {
        return new HikariDataSource(this);
    }

}