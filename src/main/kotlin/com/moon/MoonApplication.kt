package com.moon

import com.moon.properties.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(JwtProperties::class)
class MoonApplication

fun main(args: Array<String>) {
	runApplication<MoonApplication>(*args)
}

//TODO 管理好Exception
//TODO 字段常量
//TODO 更新jjwt
//TODO 分模块