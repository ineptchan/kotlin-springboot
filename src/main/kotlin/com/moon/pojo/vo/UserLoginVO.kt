package com.moon.pojo.vo

data class UserLoginVO(
    val id: Long = 0,
    val username: String,
    val token: String,
)