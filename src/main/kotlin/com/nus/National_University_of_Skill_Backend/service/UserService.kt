package com.nus.National_University_of_Skill_Backend.service

import com.nus.National_University_of_Skill_Backend.model.entity.User
import com.nus.National_University_of_Skill_Backend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers() = userRepository.findAll()
    fun getUserById(id: Long) = userRepository.findById(id).orElseThrow { RuntimeException("User not found") }
    fun saveUser(user: User) = userRepository.save(user)
    fun deleteUser(id: Long) = userRepository.deleteById(id)
}