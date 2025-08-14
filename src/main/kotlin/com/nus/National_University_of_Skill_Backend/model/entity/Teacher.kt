package com.nus.National_University_of_Skill_Backend.model.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Teacher(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column
    val field: String? = null,

    val contactInfo: String? = null,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "teacher", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val teacherLessonDetails: MutableList<TeacherLessonDetail> = mutableListOf()
)
