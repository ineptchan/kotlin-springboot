package com.moon.controller.admin

import com.moon.base.Result
import com.moon.constant.UserConstant
import com.moon.convert.toUser
import com.moon.convert.toUserVO
import com.moon.pojo.dto.UserDTO
import com.moon.pojo.dto.UserLoginDTO
import com.moon.pojo.vo.UserLoginVO
import com.moon.pojo.vo.UserVO
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
    fun getUsers(): Result<List<UserVO>> {
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
    fun getUserById(@PathVariable id: Long): Result<UserVO> {
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
    fun createUser(@Validated @RequestBody user: UserDTO): UserVO {
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
    fun updateUser(@Validated @RequestBody user: UserDTO): UserVO {
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
     * @param userLoginDTO
     * @return
     */
    @GetMapping("/login")
    fun login(@Validated @RequestBody userLoginDTO: UserLoginDTO): Result<UserLoginVO> {
        val userLoginVO = userService.loginUser(userLoginDTO)
        return Result.success(userLoginVO)
    }
}