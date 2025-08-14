package com.nus.National_University_of_Skill_Backend.service

import com.nus.National_University_of_Skill_Backend.model.entity.Lesson
import com.nus.National_University_of_Skill_Backend.repository.LessonRepository
import org.springframework.stereotype.Service

@Service
class LessonService(private val lessonRepository: LessonRepository) {
    fun getAllLessons() = lessonRepository.findAll()
    fun getLessonById(id: Long) = lessonRepository.findById(id).orElseThrow { RuntimeException("Lesson not found") }
    fun saveLesson(lesson: Lesson) = lessonRepository.save(lesson)
    fun deleteLesson(id: Long) = lessonRepository.deleteById(id)
}
