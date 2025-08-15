package com.nus.National_University_of_Skill_Backend.feature.review.service

import com.nus.National_University_of_Skill_Backend.feature.Post.PostRepository
import com.nus.National_University_of_Skill_Backend.feature.review.Review
import com.nus.National_University_of_Skill_Backend.feature.review.ReviewRepository
import com.nus.National_University_of_Skill_Backend.feature.review.dto.ReviewRequestDto
import com.nus.National_University_of_Skill_Backend.feature.teacher.TeacherRepository
import com.nus.National_University_of_Skill_Backend.feature.user.User
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val reviewRepository: ReviewRepository,
    private val postRepository: PostRepository
) {

    fun addReview(user: User, postId: Long, reviewDto: ReviewRequestDto): Review {
        val post = postRepository.findById(postId)
            .orElseThrow { RuntimeException("Post not found") }

        // check if user have one comment
        val existingReview = reviewRepository.findByUserAndPost(user, post)
        if (existingReview != null) {
            throw RuntimeException("User already reviewed this post")
        }

        val review = Review(
            user = user,
            post = post,
            rating = reviewDto.rating,
            comment = reviewDto.comment
        )
        return reviewRepository.save(review)
    }

    fun getReviewsByPost(postId: Long): List<Review> {
        val post = postRepository.findById(postId)
            .orElseThrow { RuntimeException("Post not found") }
        return reviewRepository.findByPost(post)
    }

    fun getAllReviews() = reviewRepository.findAll()

    fun getReviewById(id: Long): Review =
        reviewRepository.findById(id).orElseThrow { RuntimeException("Review not found") }

    fun deleteReview(id: Long) = reviewRepository.deleteById(id)
}
