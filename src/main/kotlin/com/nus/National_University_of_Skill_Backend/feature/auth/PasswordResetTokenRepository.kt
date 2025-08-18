package com.nus.National_University_of_Skill_Backend.feature.auth

import com.nus.National_University_of_Skill_Backend.feature.auth.entity.PasswordResetToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PasswordResetTokenRepository : JpaRepository<PasswordResetToken, Long> {
    fun findByEmailAndToken(email: String, token: String): PasswordResetToken?
    fun deleteByEmail(email: String)
}
