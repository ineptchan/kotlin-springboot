package com.moon.controller.admin

import com.moon.base.Result
import com.moon.constant.UserConstant
import com.moon.convert.toUser
import com.moon.convert.toUserVO
import com.moon.pojo.dto.UserDto
import com.moon.pojo.dto.LoginUserDto
import com.moon.pojo.vo.LoginUserVo
import com.moon.pojo.vo.UserVo
import com.moon.service.UserService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/user")
class UserController(
    private val userService: UserService,
) {
    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping
    fun getUsers(): Result<List<UserVo>> {
        val users = userService.getUsers().map { it.toUserVO() }
        return Result.Companion.success(users)
    }

    /**
     * 按id获取用户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): Result<UserVo> {
        val user = userService.getUserById(id)
        if (user == null) {
            throw Exception(UserConstant.USER_NOT_FOUND)
        }
        return Result.Companion.success(user.toUserVO())
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @PostMapping
    fun createUser(@Validated @RequestBody user: UserDto): UserVo {
        val user = userService.createUser(user.toUser())
        return user.toUserVO()
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @PutMapping
    fun updateUser(@Validated @RequestBody user: UserDto): UserVo {
        val user = userService.updateUser(user.toUser())
        return user.toUserVO()
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long) {
        userService.deleteUserById(id)
    }

    /**
     * 登录
     *
     * @param loginUserDto
     * @return
     */
    @GetMapping("/login")
    fun login(@Validated @RequestBody loginUserDto: LoginUserDto): Result<LoginUserVo> {
        val userLoginVO = userService.loginUser(loginUserDto)
        return Result.success(userLoginVO)
    }
}