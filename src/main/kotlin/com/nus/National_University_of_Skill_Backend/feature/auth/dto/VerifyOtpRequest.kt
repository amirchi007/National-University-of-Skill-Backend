package com.nus.National_University_of_Skill_Backend.feature.auth.dto

data class VerifyOtpRequest(
    val email: String,
    val code: String
)