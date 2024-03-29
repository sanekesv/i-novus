package ru.sanekesv.inovustest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/resources/**")
        .addResourceLocations("/resources/");
    registry
        .addResourceHandler("/webapp/**")
        .addResourceLocations("/webapp/");
    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:META-INF/resources/webjars/");

  }
}