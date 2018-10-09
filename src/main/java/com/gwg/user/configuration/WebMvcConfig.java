package com.gwg.user.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 继承WebMvcConfigurerAdapter采用JavaBean形式实现个性化配置定制。
 * WebMvcConfigureAdapter该抽象类里面其实没有任何的方法实现，只是空实现了接口WebMvcConfigurer内的全部方法，并没有给出任何的业务
 * 逻辑处理
 * 既然已经有了Filter级别的CORS，为什么还要CorsInterceptor呢？因为控制粒度不一样！Filter是任意Servlet的前置过滤器，
 * 而Inteceptor只对DispatcherServlet下的请求拦截有效(也就是说Interceptor只对在访问MVC方法之前进行拦截)，
 * 它是请求进入Handler的最后一道防线，如果再设置一层Inteceptor防线，可以增强安全性和可控性。
 * 
 * 关于这个阶段的CORS，不得不吐槽几句，Spring把CorsInteceptor写死在了拦截器链上的最后一个，也就是说如果我有自定义的Interceptor，
 * 请求一旦被我自己的拦截器拦截下来，则只能通过CorsFilter授权跨域，压根走不到CorsInterceptor，至于为什么，下面会讲到。
 * 
 * 所以说CorsInterceptor是专为授权Handler中的跨域而写的,handler中的跨域使用@CrossOrigin进行来支持。
 * 
 * 结论：CorsInterceptor需要配置@CrossOrigin注解器使用，可以进行更加细粒度的控制
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * 解决跨域问题，通过WebMvcConfigurerAdapter#addCorsMappings去配置
	 * Spring mvc4.2中增加了对CROS(Cross-Origin Resource Sharing)的支持
	 * http://www.ruanyifeng.com/blog/2016/04/cors.html
	 * https://www.cnblogs.com/520playboy/p/7306008.html
	 * https://www.jianshu.com/p/d05303d34222
	 *
	 * 第二种方式，使用这种方式需要继承WebMvcConfigurerAdapter
	 * CorsInterceptor: 拦截器阶段的CORS
	 */
	  @Override
	  public void addCorsMappings(CorsRegistry registry) {
	      // 配置CorsInterceptor的CORS参数,在这里可能需要指定具体的域名，* 可能不好使
	      this.configCorsParams(registry.addMapping("/**").allowedOrigins("http://localhost:3000"));
	  }
	
	  private void configCorsParams(CorsRegistration corsRegistration) {
	      corsRegistration.allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.PUT.name())
	              .allowedHeaders(CrossOrigin.DEFAULT_ALLOWED_HEADERS)
	              .exposedHeaders(HttpHeaders.SET_COOKIE)
	              .allowCredentials(CrossOrigin.DEFAULT_ALLOW_CREDENTIALS)
	              .maxAge(CrossOrigin.DEFAULT_MAX_AGE);
	  }
	
	
	
}
