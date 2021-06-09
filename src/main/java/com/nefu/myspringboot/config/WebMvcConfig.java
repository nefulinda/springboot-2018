package com.nefu.myspringboot.config;


import com.nefu.myspringboot.interceptor.AdminInterceptor;
import com.nefu.myspringboot.interceptor.LoginInterceptor;
import com.nefu.myspringboot.interceptor.TeacherInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private TeacherInterceptor teacherInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login", "/api/welcome");
        registry.addInterceptor(teacherInterceptor)
                .addPathPatterns("/api/teacher/**");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/admin/**");
    }
    // 后端允许跨域请求
    // 生产环境下，后端应隐藏请求接口，由前端通过部署时反向代理实现跨域请求。例如通过nginx服务器
    @Bean
    @Order(0)
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource configSource
                = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(configSource);
    }
}
