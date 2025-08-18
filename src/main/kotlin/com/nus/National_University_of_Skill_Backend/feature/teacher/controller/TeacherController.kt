package com.nus.National_University_of_Skill_Backend.feature.teacher.controller

import com.nus.National_University_of_Skill_Backend.util.Mapper
import com.nus.National_University_of_Skill_Backend.feature.teacher.dto.TeacherRequestDto
import com.nus.National_University_of_Skill_Backend.feature.teacher.service.TeacherService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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

    // همه معلم‌های تاییدشده (برای همه آزاد)
    @GetMapping
    fun getAllApproved() =
        teacherService.getApprovedTeachers().map { Mapper.toTeacherResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        Mapper.toTeacherResponse(teacherService.getTeacherById(id))

    // کاربر عادی درخواست معلم شدن میده → وضعیت PENDING
    @PostMapping
    fun requestTeacher(@Valid @RequestBody dto: TeacherRequestDto) =
        Mapper.toTeacherResponse(teacherService.savePendingTeacher(Mapper.toTeacherEntity(dto)))

    // فقط ادمین: لیست معلم‌های در انتظار تایید
    @GetMapping("/pending")
    @PreAuthorize("hasRole('ADMIN')")
    fun getPendingTeachers() =
        teacherService.getPendingTeachers().map { Mapper.toTeacherResponse(it) }

    // فقط ادمین: تایید معلم
    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    fun approveTeacher(@PathVariable id: Long): ResponseEntity<String> {
        teacherService.approveTeacher(id)
        return ResponseEntity.ok("Teacher approved")
    }

    // فقط ادمین: رد معلم
    @PostMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    fun rejectTeacher(@PathVariable id: Long): ResponseEntity<String> {
        teacherService.rejectTeacher(id)
        return ResponseEntity.ok("Teacher rejected")
    }

    // فقط ادمین می‌تونه حذف کنه
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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

