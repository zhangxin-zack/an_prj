package com.scorer.feign.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Encoder> firstFilterRegistration(){
        FilterRegistrationBean<Encoder> registration = new FilterRegistrationBean<>();
        registration.setFilter(new Encoder());
        registration.addUrlPatterns("/*");
        registration.setName("Encoder");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<AuthFeignFilter> secondFilterRegistration(){
        FilterRegistrationBean<AuthFeignFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AuthFeignFilter());
        registration.addUrlPatterns("/*");
        registration.setName("AuthFeignFilter");
        registration.setOrder(2);
        return registration;
    }

}
