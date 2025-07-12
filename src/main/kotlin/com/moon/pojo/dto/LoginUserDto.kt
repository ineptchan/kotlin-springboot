package com.moon.pojo.dto

import com.moon.pojo.validated.ValidUsername
import com.moon.pojo.validated.ValidatedPassword

data class LoginUserDto(
    @field:ValidUsername
    val username: String,

    @field:ValidatedPassword
    val password: String,
)