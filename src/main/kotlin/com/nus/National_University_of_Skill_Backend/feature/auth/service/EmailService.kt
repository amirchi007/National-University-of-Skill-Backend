package com.nus.National_University_of_Skill_Backend.feature.auth.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val mailSender: JavaMailSender) {
    fun sendOtpEmail(to: String, otp: String) {
//        val message = SimpleMailMessage()
//        message.setTo(to)
//        message.setSubject("Verify your account")
//        message.setText("Your OTP code is: $otp")
//        mailSender.send(message)

        println("OTP for $to: $otp")
    }
}
