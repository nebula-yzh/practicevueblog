package com.nebula.practicevueblog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nebula
 * @date 2021/10/25 21:36
 * @description: MybatisPlus配置类，配置分页插件
 *
 * 通过@mapperScan注解指定要变成实现类的接口所在的包，
 * 然后包下面的所有接口在编译之后都会生成相应的实现类
 *
 * paginationInterceptor分页插件
 */
@Configuration
@MapperScan("com.nebula.practicevueblog.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
