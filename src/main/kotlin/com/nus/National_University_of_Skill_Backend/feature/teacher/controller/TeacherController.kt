package com.nus.National_University_of_Skill_Backend.feature.teacher.controller

import com.nus.National_University_of_Skill_Backend.util.Mapper
import com.nus.National_University_of_Skill_Backend.feature.teacher.dto.TeacherRequestDto
import com.nus.National_University_of_Skill_Backend.feature.teacher.service.TeacherService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teachers")
class TeacherController(private val teacherService: TeacherService) {

    @GetMapping
    fun getAll() = teacherService.getAllTeachers().map { Mapper.toTeacherResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = Mapper.toTeacherResponse(teacherService.getTeacherById(id))

    @PostMapping
    fun create(@Valid @RequestBody dto: TeacherRequestDto) =
        Mapper.toTeacherResponse(teacherService.saveTeacher(Mapper.toTeacherEntity(dto)))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = teacherService.deleteTeacher(id)

    // Pagination
    @GetMapping("/search")
    fun searchByName(
        @RequestParam name: String,
        @RequestParam page: Int,
        @RequestParam size: Int
    ) = teacherService.searchTeachersByName(name, page, size).map { Mapper.toTeacherResponse(it) }

    @GetMapping("/search/field")
    fun searchByField(
        @RequestParam field: String,
        @RequestParam page: Int,
        @RequestParam size: Int
    ) = teacherService.searchTeachersByField(field, page, size).map { Mapper.toTeacherResponse(it) }
}
