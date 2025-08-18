package com.nus.National_University_of_Skill_Backend.feature.auth.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
@Table(name = "refresh_tokens")
data class RefreshToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val token: String = "",

    @Column(nullable = false)
    val email: String = "",

    @Column(nullable = false)
    val expiryDate: LocalDateTime
//    = LocalDateTime.now().plusDays(7)
)
