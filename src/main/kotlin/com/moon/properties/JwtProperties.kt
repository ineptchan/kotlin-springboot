package com.moon.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "com.moon.jwt")
data class JwtProperties @ConstructorBinding constructor(
    val adminSecretKey: String,
    val adminTtl: Long,
    val adminTokenName: String
)