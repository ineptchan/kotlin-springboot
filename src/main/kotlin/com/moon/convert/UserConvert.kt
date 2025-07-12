package com.moon.convert

import com.moon.pojo.dto.UserDto
import com.moon.pojo.entity.User
import com.moon.pojo.vo.UserVo
import com.moon.utils.PasswordUtil

fun User.toUserVO() = UserVo(
    id = this.id,
    username = this.username,
)

fun UserDto.toUser(): User {
    //TODO 密码加盐或者使用BCrypt

    return User(
        id = this.id,
        username = this.username,
        password = PasswordUtil.formatPassword(this.password),
    )
}