package wang.raye.separate.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid的DataResource配置类
 * @author Raye
 * @since 2016年10月7日14:14:18
 */
@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration  implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver1;
    private RelaxedPropertyResolver propertyResolver2;

    public DataBaseConfiguration(){
    	System.out.println("####################  DataBaseConfiguration");
    }
    public void setEnvironment(Environment env) {
        this.propertyResolver1 = new RelaxedPropertyResolver(env, "spring.master.");
        this.propertyResolver2 = new RelaxedPropertyResolver(env, "spring.slave.");
    }

    public DataSource master() {
        System.out.println("注入Master druid！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(propertyResolver1.getProperty("url"));
        datasource.setDriverClassName(propertyResolver1.getProperty("driver-class-name"));
        datasource.setUsername(propertyResolver1.getProperty("username"));
        datasource.setPassword(propertyResolver1.getProperty("password"));
        datasource.setInitialSize(Integer.valueOf(propertyResolver1.getProperty("initial-size")));
        datasource.setMinIdle(Integer.valueOf(propertyResolver1.getProperty("min-idle")));
        datasource.setMaxWait(Long.valueOf(propertyResolver1.getProperty("max-wait")));
        datasource.setMaxActive(Integer.valueOf(propertyResolver1.getProperty("max-active")));
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver1.getProperty("min-evictable-idle-time-millis")));
        try {
			datasource.setFilters("stat,wall");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return datasource;
    }

    public DataSource slave() {
        System.out.println("Slave druid！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(propertyResolver2.getProperty("url"));
        datasource.setDriverClassName(propertyResolver2.getProperty("driver-class-name"));
        datasource.setUsername(propertyResolver2.getProperty("username"));
        datasource.setPassword(propertyResolver2.getProperty("password"));
        datasource.setInitialSize(Integer.valueOf(propertyResolver2.getProperty("initial-size")));
        datasource.setMinIdle(Integer.valueOf(propertyResolver2.getProperty("min-idle")));
        datasource.setMaxWait(Long.valueOf(propertyResolver2.getProperty("max-wait")));
        datasource.setMaxActive(Integer.valueOf(propertyResolver2.getProperty("max-active")));
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver2.getProperty("min-evictable-idle-time-millis")));
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }
    
    @Bean
	public DynamicDataSource dynamicDataSource() {
    	DataSource master = master();
		DataSource slave = slave();
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put(DynamicDataSource.DatabaseType.Master, master);
		targetDataSources.put(DynamicDataSource.DatabaseType.Slave, slave);

		DynamicDataSource dataSource = new DynamicDataSource();
        // 该方法是AbstractRoutingDataSource的方法
		dataSource.setTargetDataSources(targetDataSources);
		dataSource.setDefaultTargetDataSource(master);
		return dataSource;
	}


}
