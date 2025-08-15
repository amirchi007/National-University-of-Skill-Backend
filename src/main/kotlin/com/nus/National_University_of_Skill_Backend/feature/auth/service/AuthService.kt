package com.nus.National_University_of_Skill_Backend.feature.auth.service

import com.nus.National_University_of_Skill_Backend.feature.auth.dto.AuthResponse
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.ForgetPasswordRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.LoginRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.RegisterRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.ResetPasswordRequest
import com.nus.National_University_of_Skill_Backend.security.JwtTokenProvider
import com.nus.National_University_of_Skill_Backend.feature.user.User
import com.nus.National_University_of_Skill_Backend.feature.user.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {

    // token for reset password: token -> email
    private val resetTokens = mutableMapOf<String, String>()

    fun register(request: RegisterRequest): AuthResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists")
        }

        val user = User(
            email = request.email,
            password = passwordEncoder.encode(request.password)
        )

        userRepository.save(user)
        val token = jwtTokenProvider.generateToken(user.email)

        return AuthResponse(token, user.email, user.role.name, user.isVerified)
    }

    fun login(request: LoginRequest): AuthResponse {
        val user = userRepository.findByEmail(request.email)
            .orElseThrow { RuntimeException("User not found") }

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw RuntimeException("Invalid password")
        }

        val token = jwtTokenProvider.generateToken(user.email)
        return AuthResponse(token, user.email, user.role.name, user.isVerified)
    }

    fun forgetPassword(request: ForgetPasswordRequest): String {
        val user = userRepository.findByEmail(request.email)
            .orElseThrow { RuntimeException("User not found") }

        val token = UUID.randomUUID().toString()
        resetTokens[token] = user.email
        // send email here
        return token
    }

    fun resetPassword(request: ResetPasswordRequest) {
        val email = resetTokens[request.token] ?: throw RuntimeException("Invalid token")
        val user = userRepository.findByEmail(email)
            .orElseThrow { RuntimeException("User not found") }

        user.password = passwordEncoder.encode(request.newPassword)
        userRepository.save(user)

        resetTokens.remove(request.token)
    }
}
