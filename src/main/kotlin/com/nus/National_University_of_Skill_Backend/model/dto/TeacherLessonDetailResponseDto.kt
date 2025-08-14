package com.nus.National_University_of_Skill_Backend.model.dto

data class TeacherLessonDetailResponseDto(
    val id: Long,
    val teacherId: Long,
    val lessonId: Long,
    val teachingType: String?,
    val hasNotes: Boolean,
    val notesDescription: String?,
    val hasProject: Boolean,
    val projectDescription: String?,
    val hasAttendance: Boolean,
    val attendanceDescription: String?,
    val hasExam: Boolean,
    val examDescription: String?,
    val conclusion: String
)