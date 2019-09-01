package com.scorer.clientPhone._Spring;

import com.scorer.clientPhone.filter.AuthClientFilter;
import com.scorer.clientPhone.filter.Encoder;
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
    public FilterRegistrationBean<AuthClientFilter> secondFilterRegistration(){
        FilterRegistrationBean<AuthClientFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AuthClientFilter());
        registration.addUrlPatterns("/*");
        registration.setName("AuthClientFilter");
        registration.setOrder(2);
        return registration;
    }

}
