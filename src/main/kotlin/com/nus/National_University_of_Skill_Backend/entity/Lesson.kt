package com.nus.National_University_of_Skill_Backend.entity

import jakarta.persistence.*

@Entity
data class Lesson(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column
    val field: String? = null,

    @OneToMany(mappedBy = "lesson", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val teacherLessonDetails: MutableList<TeacherLessonDetail> = mutableListOf()
)
