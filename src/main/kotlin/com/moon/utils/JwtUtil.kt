package com.moon.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.nio.charset.StandardCharsets
import java.util.Date

object JwtUtil {
    /**
     * 生成 JWT，使用 HS256 算法，私钥使用固定秘钥
     *
     * @param secretKey  JWT 秘钥
     * @param ttlMillis  JWT 过期时间（毫秒）
     * @param claims     要设置的自定义声明
     * @return            签名后的 JWT 字符串
     */
    fun createJWT(
        secretKey: String,
        ttlMillis: Long,
        claims: Map<String, Any>
    ): String {
        // 签名算法
        val signatureAlgorithm = SignatureAlgorithm.HS256

        // 过期时间
        val expMillis = System.currentTimeMillis() + ttlMillis
        val exp = Date(expMillis)

        // 构建 JWT
        val builder: JwtBuilder = Jwts.builder()
            // 私有声明（必须在标准声明前）
            .setClaims(claims)
            // 签名算法 & 秘钥
            .signWith(signatureAlgorithm, secretKey.toByteArray(StandardCharsets.UTF_8))
            // 设置过期时间
            .setExpiration(exp)

        return builder.compact()
    }

    /**
     * 解析并验证 JWT
     *
     * @param secretKey  JWT 秘钥（需妥善保管，不能泄露）
     * @param token      待解析的 JWT 字符串
     * @return           解密后的 Claims（若验签或过期则抛异常）
     */
    fun parseJWT(
        secretKey: String,
        token: String
    ): Claims {
        return Jwts.parser()
            // 设置签名秘钥
            .setSigningKey(secretKey.toByteArray(StandardCharsets.UTF_8))
            // 解析并获取 body
            .parseClaimsJws(token)
            .body
    }
}