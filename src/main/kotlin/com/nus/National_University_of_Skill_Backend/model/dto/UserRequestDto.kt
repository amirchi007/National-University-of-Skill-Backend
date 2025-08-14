package com.nus.National_University_of_Skill_Backend.model.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserRequestDto(
    @field:Email(message = "ایمیل معتبر وارد کنید")
    @field:NotBlank(message = "ایمیل الزامی است")
    val email: String,

    @field:NotBlank(message = "رمز عبور الزامی است")
    @field:Size(min = 6, message = "رمز عبور باید حداقل 6 کاراکتر باشد")
    val password: String
)
