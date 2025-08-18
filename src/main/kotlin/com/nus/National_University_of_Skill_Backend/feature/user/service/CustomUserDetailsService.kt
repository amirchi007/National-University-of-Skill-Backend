package com.nus.National_University_of_Skill_Backend.feature.user.service

import com.nus.National_University_of_Skill_Backend.feature.user.CustomUserDetails
import com.nus.National_University_of_Skill_Backend.feature.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            .orElseThrow { UsernameNotFoundException("User not found") }
        return CustomUserDetails(user)
    }
}
