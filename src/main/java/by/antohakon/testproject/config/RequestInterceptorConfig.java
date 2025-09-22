package by.antohakon.testproject.config;

import by.antohakon.testproject.interceptors.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("method config.addInterceptor after inject bean");
        registry.addInterceptor(requestInterceptor)
                .addPathPatterns("/api/*");
    }

}
