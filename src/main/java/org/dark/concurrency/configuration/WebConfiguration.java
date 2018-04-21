package org.dark.concurrency.configuration;

import org.dark.concurrency.interceptor.HttpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 *
 * @author xiaozefeng
 * @date 2018/4/22 上午12:03
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * spring boot 2.x 添加拦截器的方式
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
