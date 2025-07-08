package com.moon.interceptor

import com.moon.base.BaseContext
import com.moon.constant.JwtClaimsConstant
import com.moon.properties.JwtProperties
import com.moon.utils.JwtUtil.parseJWT
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor


@Component
class JwtTokenAdminInterceptor(
    private val jwtProperties: JwtProperties,
) : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (handler !is HandlerMethod) {
            // 当前拦截到的不是动态方法，直接放行
            return true
        }

        //从请求头中获取令牌
        val token = request.getHeader(jwtProperties.adminTokenName)

        //校验令牌
        try {
            val claims = parseJWT(jwtProperties.adminSecretKey, token)
            val empId = claims.get(JwtClaimsConstant.USER_ID).toString().toLong()

            //把id存入线程存储
            BaseContext.setCurrentId(empId)

            //放行
            return true
        } catch (ex: Exception) {
            //不通过 响应401状态码
            response.status = 401
            return false
        }
    }
}