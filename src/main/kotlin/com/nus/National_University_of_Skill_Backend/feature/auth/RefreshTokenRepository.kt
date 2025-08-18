package com.nus.National_University_of_Skill_Backend.feature.auth

import com.nus.National_University_of_Skill_Backend.feature.auth.entity.RefreshToken
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : JpaRepository<RefreshToken, Long> {

    fun findByToken(token: String): RefreshToken?

    @Modifying
    @Transactional
    @Query("delete from RefreshToken rt where rt.email = :email")
    fun deleteAllByEmail(@Param("email") email: String): Int
}
