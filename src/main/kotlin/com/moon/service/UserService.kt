package com.moon.service

import com.moon.pojo.dto.LoginUserDto
import com.moon.pojo.entity.User
import com.moon.pojo.vo.LoginUserVo

interface UserService {
    fun getUsers(): List<User>
    fun getUserById(id: Long): User?
    fun createUser(user: User): User
    fun updateUser(user: User): User
    fun deleteUserById(id: Long)
    fun loginUser(loginUserDto: LoginUserDto): LoginUserVo
}