package com.nus.National_University_of_Skill_Backend.feature.teacher.service

import com.nus.National_University_of_Skill_Backend.feature.teacher.Teacher
import com.nus.National_University_of_Skill_Backend.feature.teacher.TeacherRepository
import com.nus.National_University_of_Skill_Backend.feature.Post.PostStatus
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class TeacherService(private val teacherRepository: TeacherRepository) {

    fun getApprovedTeachers() =
        teacherRepository.findByStatus(PostStatus.APPROVED)

    fun getTeacherById(id: Long) =
        teacherRepository.findById(id).orElseThrow { RuntimeException("Teacher not found") }

    fun savePendingTeacher(teacher: Teacher): Teacher {
        teacher.status = PostStatus.PENDING
        return teacherRepository.save(teacher)
    }

    fun approveTeacher(id: Long) {
        val teacher = getTeacherById(id)
        teacher.status = PostStatus.APPROVED
        teacherRepository.save(teacher)
    }

    fun rejectTeacher(id: Long) {
        val teacher = getTeacherById(id)
        teacher.status = PostStatus.REJECTED
        teacherRepository.save(teacher)
    }

    fun getPendingTeachers() =
        teacherRepository.findByStatus(PostStatus.PENDING)

    fun deleteTeacher(id: Long) =
        teacherRepository.deleteById(id)

    fun searchTeachersByName(name: String, page: Int, size: Int) =
        teacherRepository.findByNameContainingIgnoreCaseAndStatus(
            name,
            PostStatus.APPROVED,
            PageRequest.of(page, size)
        ).content

    fun searchTeachersByField(field: String, page: Int, size: Int) =
        teacherRepository.findByFieldContainingIgnoreCaseAndStatus(
            field,
            PostStatus.APPROVED,
            PageRequest.of(page, size)
        ).content
}
