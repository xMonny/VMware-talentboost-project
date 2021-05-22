package talentboost.vmware.communication.database.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class DBConfiguration {

    public DataSource getDataSource() {
        Properties properties = new Properties();
        ClassPathResource databasePropResource = new ClassPathResource("application.properties");
        try (InputStream inputStream = databasePropResource.getInputStream()) {
            properties.load(inputStream);
            String user = properties.getProperty("spring.datasource.data-username");
            String pass = properties.getProperty("spring.datasource.data-password");
            String driver = properties.getProperty("spring.datasource.driver-class-name");
            String url = properties.getProperty("spring.datasource.url");
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName(driver)
                    .url(url)
                    .username(user)
                    .password(pass);
            return dataSourceBuilder.build();
        } catch (IOException e) {
            throw new IllegalStateException("Error occurred in database configuration");
        }
    }
}
