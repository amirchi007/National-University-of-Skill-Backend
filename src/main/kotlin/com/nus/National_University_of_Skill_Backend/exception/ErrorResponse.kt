package com.nus.National_University_of_Skill_Backend.exception

import java.time.LocalDateTime

data class ErrorResponse(val status: Int, val message: String, val timestamp: LocalDateTime = LocalDateTime.now())