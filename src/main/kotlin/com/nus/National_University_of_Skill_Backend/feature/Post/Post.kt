package com.nus.National_University_of_Skill_Backend.feature.Post

import com.nus.National_University_of_Skill_Backend.feature.lesson.Lesson
import com.nus.National_University_of_Skill_Backend.feature.review.Review
import com.nus.National_University_of_Skill_Backend.feature.teacher.Teacher
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
data class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    val teacher: Teacher,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    val lesson: Lesson,

    @Column(columnDefinition = "TEXT")
    val teachingType: String? = null,

    val hasNotes: Boolean = false,
    @Column(length = 255)
    val notesDescription: String? = null,

    val hasProject: Boolean = false,
    @Column(length = 255)
    val projectDescription: String? = null,

    val hasAttendance: Boolean = false,
    @Column(length = 255)
    val attendanceDescription: String? = null,

    val hasExam: Boolean = false,
    @Column(columnDefinition = "TEXT")
    val examDescription: String? = null,

    @Column(columnDefinition = "TEXT")
    val conclusion: String,

    @Column(nullable = false)
    var isPublished: Boolean = false,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val reviews: MutableList<Review> = mutableListOf()
)