package com.gwg.user.configuration;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 描述： 使用Filter 处理跨域请求，即CORS（跨来源资源共享）。
 * 第二种方式，需要配置@CrossOrigin注解器使用，可以进行更加细粒度的控制
 *
 */
@Configuration
public class AspectConfig {
	

	/**
	 * 第一种配置方式
	 * 过滤器配置，比如可以配置cat
	 * 思考：第一种方式与第二种方式的区别
	 * @return
	 */
/*	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		// 注册CORS过滤器
		UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
		configurationSource.registerCorsConfiguration("/**", this.buildConfig());
		CorsFilter corsFilter = new CorsFilter(configurationSource);
		return new FilterRegistrationBean(corsFilter);
	}

	private CorsConfiguration buildConfig() {
		
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.applyPermitDefaultValues();
		// 允许跨域访问的域名,比如设置为"http://localhost:3000"表示只允许指定的域访问
		corsConfiguration.addAllowedOrigin("*");//表示允许所有的域访问
		// 请求头
		corsConfiguration.addAllowedHeader("*");
		// 请求方法
		corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
		corsConfiguration.addAllowedMethod(HttpMethod.POST);
		corsConfiguration.addAllowedMethod(HttpMethod.GET);
		corsConfiguration.addAllowedMethod(HttpMethod.PUT);
		corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
		corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
		// 预检请求的有效期，单位为秒。
		corsConfiguration.setMaxAge(3600L);
		
		 *  是否支持用户凭证，这个也可以在前端ajax请求时设置，与后端设置等效
		 *  该字段可选。它的值是一个布尔值，表示是否允许发送Cookie。默认情况下，Cookie不包括在CORS请求之中。
		 *  设为true，即表示服务器明确许可，Cookie可以包含在请求中，一起发给服务器。
		 *  这个值也只能设为true，如果服务器不要浏览器发送Cookie，不设置该字段即可。
		 
		//corsConfiguration.setAllowCredentials(true);
		
		corsConfiguration.setExposedHeaders(Arrays.asList(HttpHeaders.SET_COOKIE));

		return corsConfiguration;
	}*/
	
	
	/*
	  //第二种方式，使用这种方式需要继承WebMvcConfigurerAdapter
	  @Override
	  public void addCorsMappings(CorsRegistry registry) {
	      // 配置CorsInterceptor的CORS参数
	      this._configCorsParams(registry.addMapping("/**"));
	  }
	
	  private void _configCorsParams(CorsRegistration corsRegistration) {
	      corsRegistration.allowedOrigins(CrossOrigin.DEFAULT_ORIGINS)
	              .allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.PUT.name())
	              .allowedHeaders(CrossOrigin.DEFAULT_ALLOWED_HEADERS)
	              .exposedHeaders(HttpHeaders.SET_COOKIE)
	              .allowCredentials(CrossOrigin.DEFAULT_ALLOW_CREDENTIALS)
	              .maxAge(CrossOrigin.DEFAULT_MAX_AGE);
	  }*/
	
	
	
}
