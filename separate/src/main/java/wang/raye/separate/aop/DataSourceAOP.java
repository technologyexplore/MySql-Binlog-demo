package wang.raye.separate.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import wang.raye.separate.config.DynamicDataSource;

/**
 * 数据源的切入面
 *
 */
@Aspect
@Component
@Slf4j
public class DataSourceAOP {

    @Before("(@annotation(wang.raye.separate.annotation.Master) || execution(* wang.raye.separate.service..*.insert*(..)) || " +
            "execution(* wang.raye.separate.service..*.update*(..)) || execution(* wang.raye.separate.service..*.delete*(..)) || " +
            "execution(* wang.raye.separate.service..*.add*(..))) && !@annotation(wang.raye.separate.annotation.Slave) -")
    public void setWriteDataSourceType() {
        DynamicDataSource.master();
        log.info("dataSource切换到：master");
    }

    @Before("(@annotation(wang.raye.separate.annotation.Slave) || execution(* wang.raye.separate.service..*.select*(..)) || execution(* wang.raye.separate.service..*.get*(..))) && !@annotation(wang.raye.separate.annotation.Master)")
    public void setReadDataSourceType() {
        DynamicDataSource.slave();
        log.info("dataSource切换到：slave");
    }


}
