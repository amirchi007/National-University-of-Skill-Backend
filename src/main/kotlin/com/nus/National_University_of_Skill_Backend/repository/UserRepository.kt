package com.nus.National_University_of_Skill_Backend.repository

import com.nus.National_University_of_Skill_Backend.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmailOrPhone(emailOrPhone: String): User?
}
