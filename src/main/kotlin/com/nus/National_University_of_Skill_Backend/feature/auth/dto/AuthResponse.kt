package com.nus.National_University_of_Skill_Backend.feature.auth.dto

import com.nus.National_University_of_Skill_Backend.feature.user.Role

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val email: String,
    val role: Role,
    val isVerified: Boolean,
)
