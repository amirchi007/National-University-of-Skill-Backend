package com.nus.National_University_of_Skill_Backend.model.dto

import com.nus.National_University_of_Skill_Backend.model.entity.Role
import java.time.LocalDateTime

data class UserResponseDto(
    val id: Long,
    val email: String,
    val role: Role,
    val isVerified: Boolean,
    val createdAt: LocalDateTime
)
