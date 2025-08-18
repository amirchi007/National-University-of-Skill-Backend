package com.nus.National_University_of_Skill_Backend.feature.auth.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ResetPasswordRequest(
    @field:NotBlank
    val email: String, // otp that send to email

    @field:NotBlank
    val token: String, // code that get from the email

    @field:NotBlank
    @field:Size(min = 6, message = "Password must be at least 6 characters")
    val newPassword: String
)
