package com.erp.config;

import com.erp.interceptor.NoCacheInterceptor;
import com.erp.interceptor.UserAuthenticationInterceptor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.CacheControl;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@EnableWebMvc
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(new String[]{"GET", "POST", "PATCH", "PUT", "DELETE"})
                .allowCredentials(true)
        ;
    }

    final String[] excludeLoginPatterns = {
            "/login/**",
            "/logout/*"
    };

    final String[] excludeResourcePatterns = {
            "static/**",
            "resources/**",
            "resource/**",
            "**/favicon.ico",
    };

    //===================================
    /**
     * 로그인 안함
     */
    final String[] notAuthenticationHandlerMapping = {
            "/login/LoginPage",
            "/login/LogoutPage",
            "/connection/*",
            "/api/*",
    };


    /**
     * 일반 사용자 권한
     */
    final String[] userAuthenticationHandlerMapping = {
            // 관리자
            "/manager/*",
    };
    //===================================

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // WebMvcConfigurer.super.addInterceptors(registry);

        registry.addInterceptor(noCacheInterceptor())
                .addPathPatterns(notAuthenticationHandlerMapping)
                .addPathPatterns(userAuthenticationHandlerMapping)
                .excludePathPatterns("/")
                .excludePathPatterns(excludeResourcePatterns)
                .excludePathPatterns(excludeLoginPatterns);

        registry.addInterceptor(userAuthenticationInterceptor())
                .addPathPatterns(userAuthenticationHandlerMapping)
                .excludePathPatterns("/")
                .excludePathPatterns(notAuthenticationHandlerMapping)
                .excludePathPatterns(excludeResourcePatterns)
                .excludePathPatterns(excludeLoginPatterns);

    }

    @Bean
    public NoCacheInterceptor noCacheInterceptor() {
        return new NoCacheInterceptor();
    }

    @Bean
    public UserAuthenticationInterceptor userAuthenticationInterceptor() {
        return new UserAuthenticationInterceptor();
    }

    /**
     * Exception Resolver
     *
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver getExceptionResolver() {
        SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();

        smer.setDefaultErrorView("etc/Exception");
        smer.setExceptionAttribute(SimpleMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE);

//        Properties prop = new Properties();
//        prop.setProperty("com.erp.exception.LoginFailException", "login/LoginFailed");
//        prop.setProperty("com.erp.exception.LoginFailThirdException", "login/LoginFailThird");
//        prop.setProperty("com.erp.exception.LoginNotException", "login/LoginRedirect");
//        prop.setProperty("com.erp.exception.LoginNotValidException", "login/LoginExpired");
//        prop.setProperty("com.erp.exception.InvalidAccessIpException", "login/LoginFailed");
//        smer.setExceptionMappings(prop);

        return smer;
    }
///////////////////////////////////////////////////////////////////////////

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setCache(true);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        registry.viewResolver(bean);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        CacheControl control = CacheControl.maxAge(1, TimeUnit.DAYS).cachePublic();

//        registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/").setCacheControl(control);
//        registry.addResourceHandler("/static/font/**").addResourceLocations("classpath:/static/font/").setCacheControl(control);
//        registry.addResourceHandler("/static/help/**").addResourceLocations("classpath:/static/help/").setCacheControl(control);
//        registry.addResourceHandler("/static/img/**").addResourceLocations("classpath:/static/img/").setCacheControl(control);
//        registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/static/js/").setCacheControl(control);

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public BeanNameViewResolver beanNameViewResolver() {
        BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
        beanNameViewResolver.setOrder(1);
        return beanNameViewResolver;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return objectMapper;
    }


    //    @Bean(value = "filterMultipartResolver")
//    public MultipartResolver filterMultipartResolver() {
//        CommonsMultipartResolver filterMultipartResolver = new CommonsMultipartResolver();
//        filterMultipartResolver.setDefaultEncoding("UTF-8");
//        return filterMultipartResolver;
//    }
//
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(10000000);
        resolver.setMaxInMemorySize(10000000);
        try {
            resolver.setUploadTempDir(fileSystemResource());
        } catch (IOException e) {
            log.error("uploadTempDir setting error.. {} | {}", e, e.getMessage());
        }
        return resolver;
    }

    @Bean
    public FileSystemResource fileSystemResource() {
        String path = "/usr/local/erp/www/upload/";
        FileSystemResource uploadDirResource = new FileSystemResource(path);
        return uploadDirResource;
    }

}
