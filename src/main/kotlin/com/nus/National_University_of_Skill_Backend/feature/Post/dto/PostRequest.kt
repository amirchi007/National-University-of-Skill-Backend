package com.nus.National_University_of_Skill_Backend.feature.Post.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class PostRequest(
    @field:NotNull(message = "teacherId is required")
    val teacherId: Long,

    @field:NotNull(message = "lessonId is required")
    val lessonId: Long,

    val teachingType: String? = null,

    val hasNotes: Boolean = false,
    val notesDescription: String? = null,

    val hasProject: Boolean = false,
    val projectDescription: String? = null,

    val hasAttendance: Boolean = false,
    val attendanceDescription: String? = null,

    val hasExam: Boolean = false,
    val examDescription: String? = null,

    @field:NotBlank(message = "conclusion is required")
    val conclusion: String
)