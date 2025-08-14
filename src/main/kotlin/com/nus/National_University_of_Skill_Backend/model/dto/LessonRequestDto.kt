package com.nus.National_University_of_Skill_Backend.model.dto

import jakarta.validation.constraints.NotBlank

data class LessonRequestDto(
    @field:NotBlank(message = "نام درس الزامی است")
    val name: String,

    val field: String? = null
)