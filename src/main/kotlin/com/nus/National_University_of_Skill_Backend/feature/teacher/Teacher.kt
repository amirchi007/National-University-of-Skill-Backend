package com.nus.National_University_of_Skill_Backend.feature.teacher

import com.nus.National_University_of_Skill_Backend.feature.Post.Post
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
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
    val teacherLessonDetails: MutableList<Post> = mutableListOf()
)