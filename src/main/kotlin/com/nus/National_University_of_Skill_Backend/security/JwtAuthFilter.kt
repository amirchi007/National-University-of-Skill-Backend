package com.nus.National_University_of_Skill_Backend.security

import com.nus.National_University_of_Skill_Backend.feature.user.UserRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolveToken(request)
        if (token != null && jwtTokenProvider.validateToken(token)) {
            val email = jwtTokenProvider.getEmail(token)
            val userDetails = userRepository.findByEmail(email)
                .orElseThrow { RuntimeException("User not found") }

            val auth = UsernamePasswordAuthenticationToken(userDetails, null, emptyList())
            SecurityContextHolder.getContext().authentication = auth
        }
        filterChain.doFilter(request, response)
    }

    private fun resolveToken(req: HttpServletRequest): String? {
        val bearer = req.getHeader("Authorization")
        return if (bearer != null && bearer.startsWith("Bearer ")) {
            bearer.substring(7)
        } else null
    }
}
