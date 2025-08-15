package com.nus.National_University_of_Skill_Backend.feature.Post.controller

import com.nus.National_University_of_Skill_Backend.feature.Post.dto.PostRequestDto
import com.nus.National_University_of_Skill_Backend.feature.lesson.service.LessonService
import com.nus.National_University_of_Skill_Backend.feature.Post.Service.PostService
import com.nus.National_University_of_Skill_Backend.feature.teacher.service.TeacherService
import com.nus.National_University_of_Skill_Backend.util.Mapper
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService,
    private val teacherService: TeacherService,
    private val lessonService: LessonService
) {
    @PatchMapping("/publish/{id}")
    fun publish(@PathVariable id: Long) = postService.publishPost(id)

    @GetMapping
    fun getAll() = postService.getAll().map { Mapper.toPostResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        Mapper.toPostResponse(postService.getById(id))

    @GetMapping("/search")
    fun search(
        @RequestParam(required = false) teacherId: Long?,
        @RequestParam(required = false) lessonId: Long?,
        @RequestParam(required = false) field: String?
    ) = postService.search(teacherId, lessonId, field).map { Mapper.toPostResponse(it) }


    @PostMapping
    fun create(@Valid @RequestBody dto: PostRequestDto) =
        Mapper.toPostResponse(
            postService.save(
                Mapper.toPostEntity(
                    dto,
                    teacherService.getTeacherById(dto.teacherId),
                    lessonService.getLessonById(dto.lessonId)
                )
            )
        )


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = postService.deleteById(id)
}