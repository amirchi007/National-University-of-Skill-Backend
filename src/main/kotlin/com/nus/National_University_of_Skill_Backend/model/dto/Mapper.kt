package com.nus.National_University_of_Skill_Backend.model.dto

import com.nus.National_University_of_Skill_Backend.model.entity.Lesson
import com.nus.National_University_of_Skill_Backend.model.entity.Review
import com.nus.National_University_of_Skill_Backend.model.entity.Teacher
import com.nus.National_University_of_Skill_Backend.model.entity.TeacherLessonDetail
import com.nus.National_University_of_Skill_Backend.model.entity.User

object Mapper {

    /* ----------- USER ----------- */
    fun toUserEntity(dto: UserRequestDto) = User(
        email = dto.email,
        password = dto.password
    )

    fun toUserResponse(entity: User) = UserResponseDto(
        id = entity.id,
        email = entity.email,
        role = entity.role,
        isVerified = entity.isVerified,
        createdAt = entity.createdAt
    )

    /* ----------- LESSON ----------- */
    fun toLessonEntity(dto: LessonRequestDto) = Lesson(
        name = dto.name,
        field = dto.field
    )

    fun toLessonResponse(entity: Lesson) = LessonResponseDto(
        id = entity.id,
        name = entity.name,
        field = entity.field
    )

    /* ----------- REVIEW ----------- */
    fun toReviewEntity(dto: ReviewRequestDto, user: User, teacherLessonDetail: TeacherLessonDetail) =
        Review(
            user = user,
            teacherLessonDetail = teacherLessonDetail,
            rating = dto.rating,
            comment = dto.comment
        )

    fun toReviewResponse(entity: Review) = ReviewResponseDto(
        id = entity.id,
        userId = entity.user.id,
        teacherLessonDetailId = entity.teacherLessonDetail.id,
        rating = entity.rating,
        comment = entity.comment
    )

    /* ----------- TEACHER ----------- */
    fun toTeacherEntity(dto: TeacherRequestDto) = Teacher(
        name = dto.name,
        field = dto.field,
        contactInfo = dto.contactInfo
    )

    fun toTeacherResponse(entity: Teacher) = TeacherResponseDto(
        id = entity.id,
        name = entity.name,
        field = entity.field,
        contactInfo = entity.contactInfo,
        createdAt = entity.createdAt
    )
    /* ----------- TEACHER LESSON DETAIL ----------- */
    fun toTeacherLessonDetailEntity(
        dto: TeacherLessonDetailRequestDto,
        teacher: Teacher,
        lesson: Lesson
    ) = TeacherLessonDetail(
        teacher = teacher,
        lesson = lesson,
        teachingType = dto.teachingType,
        hasNotes = dto.hasNotes,
        notesDescription = dto.notesDescription,
        hasProject = dto.hasProject,
        projectDescription = dto.projectDescription,
        hasAttendance = dto.hasAttendance,
        attendanceDescription = dto.attendanceDescription,
        hasExam = dto.hasExam,
        examDescription = dto.examDescription,
        conclusion = dto.conclusion
    )

    fun toTeacherLessonDetailResponse(entity: TeacherLessonDetail) =
        TeacherLessonDetailResponseDto(
            id = entity.id,
            teacherId = entity.teacher.id,
            lessonId = entity.lesson.id,
            teachingType = entity.teachingType,
            hasNotes = entity.hasNotes,
            notesDescription = entity.notesDescription,
            hasProject = entity.hasProject,
            projectDescription = entity.projectDescription,
            hasAttendance = entity.hasAttendance,
            attendanceDescription = entity.attendanceDescription,
            hasExam = entity.hasExam,
            examDescription = entity.examDescription,
            conclusion = entity.conclusion
        )
}
