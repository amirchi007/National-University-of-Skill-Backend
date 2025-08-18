package com.nus.National_University_of_Skill_Backend.feature.review.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ReviewRequestDto(
//    @field:NotNull("postId is required")
    val postId: Long,

    @field:Min(1)
    @field:Max(5)
    val rating: Int,

    @field:NotBlank
    val comment: String
)
