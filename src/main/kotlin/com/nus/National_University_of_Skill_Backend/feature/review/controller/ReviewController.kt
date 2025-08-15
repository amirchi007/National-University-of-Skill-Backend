package com.nus.National_University_of_Skill_Backend.feature.review.controller

import com.nus.National_University_of_Skill_Backend.util.Mapper
import com.nus.National_University_of_Skill_Backend.feature.review.dto.ReviewRequestDto
import com.nus.National_University_of_Skill_Backend.feature.review.dto.ReviewResponseDto
import com.nus.National_University_of_Skill_Backend.feature.review.service.ReviewService
import com.nus.National_University_of_Skill_Backend.feature.Post.Service.PostService
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
@RequestMapping("/reviews")
class ReviewController(
    private val reviewService: ReviewService,
    private val userService: UserService,
) {

    @GetMapping
    fun getAll() = reviewService.getAllReviews().map { Mapper.toReviewResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = Mapper.toReviewResponse(reviewService.getReviewById(id))


    @GetMapping("/post/{postId}")
    fun getByPost(@PathVariable postId: Long) =
        reviewService.getReviewsByPost(postId).map { Mapper.toReviewResponse(it) }

    @PostMapping
    fun create(@Valid @RequestBody dto: ReviewRequestDto): ReviewResponseDto {
        val user = userService.getUserById(1) // get from jwt
        return Mapper.toReviewResponse(reviewService.addReview(user, dto.postId, dto))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = reviewService.deleteReview(id)
}