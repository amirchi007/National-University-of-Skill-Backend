package com.nus.National_University_of_Skill_Backend.feature.auth.dto

data class AuthResponse(
    val token: String,
    val email: String,
    val role: String,
    val isVerified: Boolean
)
