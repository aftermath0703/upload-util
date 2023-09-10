package com.nt.uploadutil.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc的配置类
 *
 * @author arvin
 * @date 2023/03/10
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * 后端跨域配置
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				// 设置允许跨域请求的域名
				.allowedOriginPatterns("*")
				// 是否允许cookie
				.allowCredentials(true)
				// 设置允许的请求方式
				.allowedMethods("*")
				// 设置允许的header属性
				.allowedHeaders("*")
				// 跨域允许时间
				.maxAge(3600);
	}
}