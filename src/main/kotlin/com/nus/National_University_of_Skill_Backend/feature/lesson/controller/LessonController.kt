package com.nus.National_University_of_Skill_Backend.feature.lesson.controller

import com.nus.National_University_of_Skill_Backend.util.Mapper
import com.nus.National_University_of_Skill_Backend.feature.lesson.dto.LessonRequestDto
import com.nus.National_University_of_Skill_Backend.feature.lesson.service.LessonService
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
@RequestMapping("/lessons")
class LessonController(private val lessonService: LessonService) {

    @GetMapping
    fun getAll() = lessonService.getAllLessons().map { Mapper.toLessonResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = Mapper.toLessonResponse(lessonService.getLessonById(id))

    @PostMapping
    fun create(@Valid @RequestBody dto: LessonRequestDto) =
        Mapper.toLessonResponse(lessonService.saveLesson(Mapper.toLessonEntity(dto)))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = lessonService.deleteLesson(id)

    // Pagination
    @GetMapping("/search")
    fun searchByName(
        @RequestParam name: String,
        @RequestParam page: Int,
        @RequestParam size: Int
    ) = lessonService.searchLessonsByName(name, page, size).map { Mapper.toLessonResponse(it) }

    @GetMapping("/search/field")
    fun searchByField(
        @RequestParam field: String,
        @RequestParam page: Int,
        @RequestParam size: Int
    ) = lessonService.searchLessonsByField(field, page, size).map { Mapper.toLessonResponse(it) }
}
