package com.springboot.gateway;

import com.netflix.zuul.context.ContextLifecycleFilter;
import com.netflix.zuul.http.ZuulServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class ZuulConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ZuulConfiguration.class);

    @Bean
    public ServletRegistrationBean getZuulServlet() {
        log.info("starting ZuulServlet");
        ServletRegistrationBean servlet = new ServletRegistrationBean(new ZuulServlet());
        servlet.addUrlMappings("/*");
        //servlet.setAsyncSupported(true);
        //servlet.setLoadOnStartup(1);
        return servlet;
    }

//    @Bean
//    public FilterRegistrationBean contextLifecycleFilter() {
//        log.info("starting contextLifecycleFilter");
//        FilterRegistrationBean filter = new FilterRegistrationBean(new ContextLifecycleFilter());
//        filter.addUrlPatterns("/*");
//        return filter;
//    }




}