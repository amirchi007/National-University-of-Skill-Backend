package com.nus.National_University_of_Skill_Backend.feature.review

import com.nus.National_University_of_Skill_Backend.feature.Post.Post
import com.nus.National_University_of_Skill_Backend.feature.teacher.Teacher
import com.nus.National_University_of_Skill_Backend.feature.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : JpaRepository<Review, Long> {
    fun findByUserAndPost(user: User, post: Post): Review
    fun findByPost(post: Post): List<Review>
}

