package com.moon.base

import java.io.Serializable

data class Result<T>(
    var code: Int = 0,      // 编码：0 成功，1 和其它数字为失败
    var msg: String? = null, // 错误信息
    var data: T? = null     // 数据
) : Serializable {

    companion object {
        /**
         * 只返回成功，不带数据
         */
        fun <T> success(data: T): Result<T> = Result(code = 0, data = data)

        /**
         * 只返回错误信息
         */
        fun <T> error(msg: String): Result<T> = Result(code = 1, msg = msg)

        fun <T> error(msg: String, data: T): Result<T> = Result(code = 1, msg = msg, data = data)
    }
}