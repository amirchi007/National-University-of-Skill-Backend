package com.nus.National_University_of_Skill_Backend.model.dto

import java.time.LocalDateTime

data class TeacherResponseDto(
    val id: Long,
    val name: String,
    val field: String?,
    val contactInfo: String?,
    val createdAt: LocalDateTime
)