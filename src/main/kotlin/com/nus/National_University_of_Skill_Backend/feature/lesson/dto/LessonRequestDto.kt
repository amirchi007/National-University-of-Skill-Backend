package com.nus.National_University_of_Skill_Backend.feature.lesson.dto

import jakarta.validation.constraints.NotBlank

data class LessonRequestDto(
    @field:NotBlank(message = "name of the lesson is required")
    val name: String,

    val field: String? = null
)