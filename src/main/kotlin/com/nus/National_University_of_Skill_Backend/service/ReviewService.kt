package com.nus.National_University_of_Skill_Backend.service

import com.nus.National_University_of_Skill_Backend.model.entity.Review
import com.nus.National_University_of_Skill_Backend.repository.ReviewRepository
import org.springframework.stereotype.Service

@Service
class ReviewService(private val reviewRepository: ReviewRepository) {
    fun getAllReviews() = reviewRepository.findAll()
    fun getReviewById(id: Long) = reviewRepository.findById(id).orElseThrow { RuntimeException("Review not found") }
    fun saveReview(review: Review) = reviewRepository.save(review)
    fun deleteReview(id: Long) = reviewRepository.deleteById(id)
}