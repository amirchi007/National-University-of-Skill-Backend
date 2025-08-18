package com.nus.National_University_of_Skill_Backend.feature.auth.controller

import com.nus.National_University_of_Skill_Backend.feature.auth.dto.VerifyOtpRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.AuthResponse
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.ForgetPasswordRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.LoginRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.RefreshTokenRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.RegisterRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.ResendOtpRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.dto.ResetPasswordRequest
import com.nus.National_University_of_Skill_Backend.feature.auth.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.register(request))
    }

    @PostMapping("/verify-otp")
    fun verifyOtp(@RequestBody request: VerifyOtpRequest): ResponseEntity<String> {
        return ResponseEntity.ok(authService.verifyOtp(request.email, request.code))
    }

    @PostMapping("/resend-otp")
    fun resendOtp(@RequestBody request: ResendOtpRequest): ResponseEntity<String> {
        authService.resendOtp(request.email)
        return ResponseEntity.ok("OTP sent successfully")
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.login(request))
    }

    @PostMapping("/forget-password")
    fun forgetPassword(@Valid @RequestBody request: ForgetPasswordRequest): ResponseEntity<String> {
        authService.forgetPassword(request)
        return ResponseEntity.ok("Password reset token sent to email") 
    }


    @PostMapping("/reset-password")
    fun resetPassword(@Valid @RequestBody request: ResetPasswordRequest): ResponseEntity<String> {
        authService.resetPassword(request)
        return ResponseEntity.ok("Password successfully reset")
    }

    @PostMapping("/refresh-token")
    fun refreshToken(@Valid @RequestBody request: RefreshTokenRequest): ResponseEntity<String> {
        val newAccessToken = authService.refreshToken(request.refreshToken)
        return ResponseEntity.ok(newAccessToken)
    }



}
