package com.moon.config

import com.moon.interceptor.JwtTokenAdminInterceptor
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

/**
 * 配置类，注册web层相关组件
 */
@Configuration
class WebMvcConfiguration(
    private val jwtTokenAdminInterceptor: JwtTokenAdminInterceptor
) : WebMvcConfigurationSupport() {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun addInterceptors(registry: InterceptorRegistry) {
        log.info("开始注册自定义拦截器...");

        registry.addInterceptor(jwtTokenAdminInterceptor)
            .addPathPatterns("/admin/**")
            .excludePathPatterns("/admin/user/login");
    }
}