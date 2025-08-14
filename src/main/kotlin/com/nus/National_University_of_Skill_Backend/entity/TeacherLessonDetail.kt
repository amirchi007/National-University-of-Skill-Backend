package com.nus.National_University_of_Skill_Backend.entity

import jakarta.persistence.*

@Entity
data class TeacherLessonDetail(
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

    @OneToMany(mappedBy = "teacherLessonDetail", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val reviews: MutableList<Review> = mutableListOf()
)


