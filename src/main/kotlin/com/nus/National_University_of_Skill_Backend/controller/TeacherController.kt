package com.nus.National_University_of_Skill_Backend.controller

import com.nus.National_University_of_Skill_Backend.model.entity.Teacher
import com.nus.National_University_of_Skill_Backend.service.TeacherService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teachers")
class TeacherController(private val teacherService: TeacherService) {
    @GetMapping fun getAll() = teacherService.getAllTeachers()
    @GetMapping("/{id}") fun getById(@PathVariable id: Long) = teacherService.getTeacherById(id)
    @PostMapping fun create(@RequestBody teacher: Teacher) = teacherService.saveTeacher(teacher)
    @DeleteMapping("/{id}") fun delete(@PathVariable id: Long) = teacherService.deleteTeacher(id)
}