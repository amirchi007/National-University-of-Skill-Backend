package com.nus.National_University_of_Skill_Backend.controller

import com.nus.National_University_of_Skill_Backend.model.entity.TeacherLessonDetail
import com.nus.National_University_of_Skill_Backend.service.TeacherLessonDetailService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teacher-lessons")
class TeacherLessonDetailController(
    private val service: TeacherLessonDetailService
) {
    @PostMapping
    fun create(@RequestBody detail: TeacherLessonDetail) = service.save(detail)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = service.findById(id)

    @GetMapping
    fun getAll() = service.findAll()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}
