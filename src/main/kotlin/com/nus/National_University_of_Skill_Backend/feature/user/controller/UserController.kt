package com.nus.National_University_of_Skill_Backend.feature.user.controller

import com.nus.National_University_of_Skill_Backend.util.Mapper
import com.nus.National_University_of_Skill_Backend.feature.user.dto.UserRequestDto
import com.nus.National_University_of_Skill_Backend.feature.user.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAll() = userService.getAllUsers().map { Mapper.toUserResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = Mapper.toUserResponse(userService.getUserById(id))

    @PostMapping
    fun create(@Valid @RequestBody dto: UserRequestDto) =
        Mapper.toUserResponse(userService.saveUser(Mapper.toUserEntity(dto)))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = userService.deleteUser(id)
}