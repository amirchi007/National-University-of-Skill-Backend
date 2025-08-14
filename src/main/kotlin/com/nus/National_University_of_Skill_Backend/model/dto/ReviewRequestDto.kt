package com.nus.National_University_of_Skill_Backend.model.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class ReviewRequestDto(
    @field:NotNull(message = "شناسه استاد-درس الزامی است")
    val teacherLessonDetailId: Long,

    @field:Min(1) @field:Max(5)
    val rating: Int,

    val comment: String
)