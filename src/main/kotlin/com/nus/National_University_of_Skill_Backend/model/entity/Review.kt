package com.nus.National_University_of_Skill_Backend.model.entity

import jakarta.persistence.*

@Entity
data class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_lesson_detail_id", nullable = false)
    val teacherLessonDetail: TeacherLessonDetail,

    val rating: Int,

    @Column(columnDefinition = "TEXT")
    val comment: String
)
