package com.nus.National_University_of_Skill_Backend.feature.auth

import com.nus.National_University_of_Skill_Backend.feature.auth.entity.Otp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OtpRepository : JpaRepository<Otp, Long> {
    fun findByEmail(email: String): Otp?
    fun deleteByEmail(email: String)
}
