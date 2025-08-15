package com.nus.National_University_of_Skill_Backend.feature.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class ForgetPasswordRequest(
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Invalid email format")
    val email: String
)