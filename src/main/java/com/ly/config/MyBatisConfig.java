package com.ly.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description:
 * @author: LiYuan
 * @time: 2023/5/16 15:52
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.ly.mapper")
public class MyBatisConfig {

    /**
     * 分页插件,一缓和二缓遵循mybatis的规则
     * 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
     * paginationInnerInterceptor.setOverflow(false);
     * 设置最大单页限制数量，默认 500 条，-1 不受限制
     * paginationInnerInterceptor.setMaxLimit(500L);
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }
}
