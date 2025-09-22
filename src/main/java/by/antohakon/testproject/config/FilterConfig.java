package by.antohakon.testproject.config;

import by.antohakon.testproject.filters.MyFilter1;
import by.antohakon.testproject.filters.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyFilter1> myFirstFilter() {

           FilterRegistrationBean<MyFilter1> filterRegistrationBean = new FilterRegistrationBean<>();
           filterRegistrationBean.setFilter(new MyFilter1());
           filterRegistrationBean.addUrlPatterns("/*");
           filterRegistrationBean.setOrder(2);
           return filterRegistrationBean;
    }

   @Bean
    public FilterRegistrationBean<MyFilter2> mySecondFilter() {

        FilterRegistrationBean<MyFilter2> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new MyFilter2());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;

   }

}
