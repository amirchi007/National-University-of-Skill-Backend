package com.nus.National_University_of_Skill_Backend.model.dto

data class ReviewResponseDto(
    val id: Long,
    val userId: Long,
    val teacherLessonDetailId: Long,
    val rating: Int,
    val comment: String
)