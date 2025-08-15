package com.nus.National_University_of_Skill_Backend.feature.user.dto

import com.nus.National_University_of_Skill_Backend.feature.user.Role
import java.time.LocalDateTime

data class UserResponseDto(
    val id: Long,
    val email: String,
    val role: Role,
    val isVerified: Boolean,
    val createdAt: LocalDateTime
)
