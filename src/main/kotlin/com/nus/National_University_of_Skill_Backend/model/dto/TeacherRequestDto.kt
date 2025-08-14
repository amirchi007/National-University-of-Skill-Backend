package com.nus.National_University_of_Skill_Backend.model.dto

import jakarta.validation.constraints.NotBlank

data class TeacherRequestDto(
    @field:NotBlank(message = "نام استاد الزامی است")
    val name: String,

    val field: String? = null,
    val contactInfo: String? = null
)