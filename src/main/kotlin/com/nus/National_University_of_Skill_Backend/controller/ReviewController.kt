package com.nus.National_University_of_Skill_Backend.controller

import com.nus.National_University_of_Skill_Backend.model.entity.Review
import com.nus.National_University_of_Skill_Backend.service.ReviewService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reviews")
class ReviewController(private val reviewService: ReviewService) {
    @GetMapping fun getAll() = reviewService.getAllReviews()
    @GetMapping("/{id}") fun getById(@PathVariable id: Long) = reviewService.getReviewById(id)
    @PostMapping fun create(@RequestBody review: Review) = reviewService.saveReview(review)
    @DeleteMapping("/{id}") fun delete(@PathVariable id: Long) = reviewService.deleteReview(id)
}