package com.moon.service

import com.moon.pojo.dto.UserLoginDTO
import com.moon.pojo.entity.User
import com.moon.pojo.vo.UserLoginVO
import java.util.Optional

interface UserService {
    fun getUsers(): List<User>
    fun getUserById(id: Long): User?
    fun createUser(user: User): User
    fun updateUser(user: User): User
    fun deleteUserById(id: Long)
    fun loginUser(userLoginDTO: UserLoginDTO): UserLoginVO
}