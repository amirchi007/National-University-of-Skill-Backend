package com.nus.National_University_of_Skill_Backend.controller

import com.nus.National_University_of_Skill_Backend.model.entity.Lesson
import com.nus.National_University_of_Skill_Backend.service.LessonService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lessons")
class LessonController(private val lessonService: LessonService) {
    @GetMapping
    fun getAll() = lessonService.getAllLessons()
    @GetMapping("/{id}") fun getById(@PathVariable id: Long) = lessonService.getLessonById(id)
    @PostMapping
    fun create(@RequestBody lesson: Lesson) = lessonService.saveLesson(lesson)
    @DeleteMapping("/{id}") fun delete(@PathVariable id: Long) = lessonService.deleteLesson(id)
}