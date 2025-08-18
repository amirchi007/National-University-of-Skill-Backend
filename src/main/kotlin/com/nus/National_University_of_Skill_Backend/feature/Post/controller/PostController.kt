package com.nus.National_University_of_Skill_Backend.feature.Post.controller

import com.nus.National_University_of_Skill_Backend.feature.Post.dto.PostRequest
import com.nus.National_University_of_Skill_Backend.feature.lesson.service.LessonService
import com.nus.National_University_of_Skill_Backend.feature.Post.Service.PostService
import com.nus.National_University_of_Skill_Backend.feature.Post.dto.PostResponse
import com.nus.National_University_of_Skill_Backend.feature.teacher.service.TeacherService
import com.nus.National_University_of_Skill_Backend.util.Mapper
import jakarta.validation.Valid
import org.springframework.security.access.prepost.PreAuthorize
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

    @PostMapping
    fun create(@Valid @RequestBody dto: PostRequest): PostResponse {
        val teacher = teacherService.getTeacherById(dto.teacherId)
        val lesson = lessonService.getLessonById(dto.lessonId)
        val post = postService.save(Mapper.toPostEntity(dto, teacher, lesson))
        return Mapper.toPostResponse(post)
    }

    @GetMapping
    fun getAll(): List<PostResponse> =
        postService.getAll().map { Mapper.toPostResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): PostResponse =
        Mapper.toPostResponse(postService.getById(id))

    @GetMapping("/search")
    fun search(
        @RequestParam(required = false) teacherId: Long?,
        @RequestParam(required = false) lessonId: Long?,
        @RequestParam(required = false) field: String?
    ): List<PostResponse> =
        postService.search(teacherId, lessonId, field).map { Mapper.toPostResponse(it) }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = postService.deleteById(id)

    // فقط ادمین
    @GetMapping("/pending")
    @PreAuthorize("hasRole('ADMIN')")
    fun getPending(): List<PostResponse> =
        postService.getPendingPosts().map { Mapper.toPostResponse(it) }

    @PatchMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    fun approve(@PathVariable id: Long) = postService.approvePost(id)

    @PatchMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    fun reject(@PathVariable id: Long) = postService.rejectPost(id)
}
