package com.moon.convert

import com.moon.pojo.dto.UserDTO
import com.moon.pojo.dto.UserLoginDTO
import com.moon.pojo.entity.User
import com.moon.pojo.vo.UserVO
import com.moon.utils.PasswordUtil
import org.springframework.util.DigestUtils

fun User.toUserVO() = UserVO(
    id = this.id,
    username = this.username,
)

fun UserDTO.toUser(): User {
    //TODO 密码加盐或者使用BCrypt

    return User(
        id = this.id,
        username = this.username,
        password = PasswordUtil.formatPassword(this.password),
    )
}