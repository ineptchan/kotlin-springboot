package com.moon.repository

import com.moon.pojo.entity.User
import org.springframework.data.geo.GeoResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun existsByUsername(username: String):Boolean
    fun findByUsername(username: String): User?

}