package com.nus.National_University_of_Skill_Backend.feature.lesson.service

import com.nus.National_University_of_Skill_Backend.feature.lesson.Lesson
import com.nus.National_University_of_Skill_Backend.feature.lesson.LessonRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LessonService(private val lessonRepository: LessonRepository) {

    fun getAllLessons() = lessonRepository.findAll()

    fun getLessonById(id: Long) =
        lessonRepository.findById(id).orElseThrow { RuntimeException("Lesson not found") }

    fun saveLesson(lesson: Lesson) = lessonRepository.save(lesson)

    fun deleteLesson(id: Long) = lessonRepository.deleteById(id)

    fun searchLessonsByName(name: String, page: Int, size: Int) =
        lessonRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size)).content

    fun searchLessonsByField(field: String, page: Int, size: Int) =
        lessonRepository.findByFieldContainingIgnoreCase(field, PageRequest.of(page, size)).content
}
