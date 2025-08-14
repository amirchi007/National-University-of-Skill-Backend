package com.nus.National_University_of_Skill_Backend.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class TeacherLessonDetailRequestDto(
    @field:NotNull(message = "شناسه استاد الزامی است")
    val teacherId: Long,

    @field:NotNull(message = "شناسه درس الزامی است")
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

    @field:NotBlank(message = "نتیجه‌گیری الزامی است")
    val conclusion: String
)