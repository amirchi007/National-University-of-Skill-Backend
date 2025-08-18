package com.nus.National_University_of_Skill_Backend.util

import com.nus.National_University_of_Skill_Backend.feature.lesson.Lesson
import com.nus.National_University_of_Skill_Backend.feature.lesson.dto.LessonRequestDto
import com.nus.National_University_of_Skill_Backend.feature.lesson.dto.LessonResponseDto
import com.nus.National_University_of_Skill_Backend.feature.review.Review
import com.nus.National_University_of_Skill_Backend.feature.review.dto.ReviewRequestDto
import com.nus.National_University_of_Skill_Backend.feature.review.dto.ReviewResponseDto
import com.nus.National_University_of_Skill_Backend.feature.teacher.Teacher
import com.nus.National_University_of_Skill_Backend.feature.Post.Post
import com.nus.National_University_of_Skill_Backend.feature.Post.dto.PostRequest
import com.nus.National_University_of_Skill_Backend.feature.Post.dto.PostResponse
import com.nus.National_University_of_Skill_Backend.feature.teacher.dto.TeacherRequestDto
import com.nus.National_University_of_Skill_Backend.feature.teacher.dto.TeacherResponseDto
import com.nus.National_University_of_Skill_Backend.feature.user.User
import com.nus.National_University_of_Skill_Backend.feature.user.dto.UserRequestDto
import com.nus.National_University_of_Skill_Backend.feature.user.dto.UserResponseDto

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
    fun toReviewEntity(dto: ReviewRequestDto, user: User, teacherLessonDetail: Post) =
        Review(
            user = user,
            post = teacherLessonDetail,
            rating = dto.rating,
            comment = dto.comment
        )

    fun toReviewResponse(entity: Review) = ReviewResponseDto(
        id = entity.id,
        userId = entity.user.id,
        teacherLessonDetailId = entity.post.id,
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
    fun toPostEntity(
        dto: PostRequest,
        teacher: Teacher,
        lesson: Lesson
    ) = Post(
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

    fun toPostResponse(entity: Post) =
        PostResponse(
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