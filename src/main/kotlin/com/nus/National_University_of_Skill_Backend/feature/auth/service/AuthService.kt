package com.nus.National_University_of_Skill_Backend.feature.auth.service

import com.nus.National_University_of_Skill_Backend.feature.auth.OtpRepository
import com.nus.National_University_of_Skill_Backend.feature.auth.PasswordResetTokenRepository
import com.nus.National_University_of_Skill_Backend.feature.auth.RefreshTokenRepository
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.AuthResponse
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.ForgetPasswordRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.LoginRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.RegisterRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.ResetPasswordRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.entity.Otp
import com.nus.National_University_of_Skill_Backend.feature.auth.entity.PasswordResetToken
import com.nus.National_University_of_Skill_Backend.feature.auth.entity.RefreshToken
import com.nus.National_University_of_Skill_Backend.security.JwtTokenProvider
import com.nus.National_University_of_Skill_Backend.feature.user.User
import com.nus.National_University_of_Skill_Backend.feature.user.UserRepository
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Transactional
@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val otpRepository: OtpRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val passwordResetTokenRepository: PasswordResetTokenRepository,
    private val emailService: EmailService
) {

    fun register(request: RegisterRequest): AuthResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists")
        }

        val user = User(
            email = request.email,
            password = passwordEncoder.encode(request.password),
            isVerified = false
        )
        userRepository.save(user)

        val otpCode = (100000..999999).random().toString()
        otpRepository.deleteByEmail(user.email) // delete old otp
        otpRepository.save(
            Otp(
                email = user.email,
                code = otpCode,
                expiresAt = LocalDateTime.now()
//                    .plusMinutes(5)
            )
        )
        emailService.sendOtpEmail(user.email, otpCode)

        val accessToken = jwtTokenProvider.generateAccessToken(user.email)
        val refreshToken = jwtTokenProvider.generateRefreshToken(user.email)

        refreshTokenRepository.save(
            RefreshToken(
                token = refreshToken,
                email = user.email,
                expiryDate = LocalDateTime.now().plusDays(30)
            )
        )

        return AuthResponse(accessToken, refreshToken, user.email, user.role, user.isVerified)
    }

    fun login(request: LoginRequest): AuthResponse {

        val user = userRepository.findByEmail(request.email).orElseThrow { RuntimeException("User not found") }

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw RuntimeException("Invalid password")
        }

        val accessToken = jwtTokenProvider.generateAccessToken(user.email)
        val refreshToken = jwtTokenProvider.generateRefreshToken(user.email)

        refreshTokenRepository.deleteAllByEmail(user.email)
        refreshTokenRepository.save(
            RefreshToken(
                token = refreshToken,
                email = user.email,
                expiryDate = LocalDateTime.now().plusDays(30)
            )
        )

        return AuthResponse(accessToken, refreshToken, user.email, user.role, user.isVerified)
    }

    fun forgetPassword(request: ForgetPasswordRequest): String {

        val user = userRepository.findByEmail(request.email).orElseThrow { RuntimeException("User not found with email ${request.email}") }


        passwordResetTokenRepository.deleteByEmail(user.email)


        val token = (100000..999999).random().toString()
        passwordResetTokenRepository.save(
            PasswordResetToken(
                email = user.email,
                token = token,
                expiresAt = LocalDateTime.now().plusMinutes(10)
            )
        )

        emailService.sendOtpEmail(user.email, token)
        return "success"
    }

    fun resetPassword(request: ResetPasswordRequest) {
        val resetToken = passwordResetTokenRepository.findByEmailAndToken(request.email, request.token)
            ?.takeIf { it.expiresAt.isAfter(LocalDateTime.now()) }
            ?: throw RuntimeException("Invalid or expired token")

        val user = userRepository.findByEmail(request.email).orElseThrow()
        user.password = passwordEncoder.encode(request.newPassword)
        userRepository.save(user)


        passwordResetTokenRepository.delete(resetToken)
    }

    fun refreshToken(refreshToken: String): String {

        val savedToken =
            refreshTokenRepository.findByToken(refreshToken) ?: throw RuntimeException("Invalid refresh token")

        if (savedToken.expiryDate.isBefore(LocalDateTime.now())) {
            throw RuntimeException("Refresh token expired")
        }

        return jwtTokenProvider.generateAccessToken(savedToken.email)
    }

    fun verifyOtp(email: String, code: String): String {
        val otp = otpRepository.findByEmail(email) ?: throw RuntimeException("OTP not found")

        if (otp.expiresAt.isBefore(LocalDateTime.now())) {
            throw RuntimeException("OTP expired")
        }

        if (otp.code != code) {
            throw RuntimeException("Invalid OTP code")
        }

        val user = userRepository.findByEmail(email).orElseThrow { RuntimeException("User not found") }
        user.isVerified = true
        userRepository.save(user)

        otpRepository.delete(otp) //delete otp after sending

        return "Email verified successfully!"
    }

    fun resendOtp(email: String) {

        otpRepository.deleteByEmail(email)


        val otpCode = (100000..999999).random().toString()
        val otp = Otp(
            email = email,
            code = otpCode,
            expiresAt = LocalDateTime.now().plusMinutes(5)
        )
        otpRepository.save(otp)

    }
}
