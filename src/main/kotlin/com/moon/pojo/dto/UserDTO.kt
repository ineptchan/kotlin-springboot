package com.moon.pojo.dto

import com.moon.pojo.validated.ValidUsername
import com.moon.pojo.validated.ValidatedPassword
import jakarta.validation.constraints.PositiveOrZero

data class UserDTO(
    @field:PositiveOrZero(message = "错误的id")
    val id: Long = 0,

    @field:ValidUsername
    val username: String,

    @field:ValidatedPassword
    val password: String,
)