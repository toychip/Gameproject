//package com.gameproject.flash.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.session.web.http.CookieHttpSessionIdResolver;
//import org.springframework.session.web.http.DefaultCookieSerializer;
//import org.springframework.session.web.http.HttpSessionIdResolver;
//
//@Configuration
//public class HttpSessionConfig {
//
//    @Bean
//    public DefaultCookieSerializer defaultCookieSerializer() {
//        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//        serializer.setSameSite("none"); // SameSite를 "none"으로 설정
//        return serializer;
//    }
//
//    @Bean
//    public HttpSessionIdResolver httpSessionIdResolver(DefaultCookieSerializer defaultCookieSerializer) {
//        CookieHttpSessionIdResolver resolver = new CookieHttpSessionIdResolver();
//        resolver.setCookieSerializer(defaultCookieSerializer);
//        return resolver;
//    }
//}
