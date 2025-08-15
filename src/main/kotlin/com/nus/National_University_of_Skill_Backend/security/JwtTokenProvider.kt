package com.nus.National_University_of_Skill_Backend.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider {

    private val secretKey = "mySecretKey12345" // application.yml
    private val validityInMs = 3600000 // 1 hour

    fun generateToken(email: String): String {
        val now = Date()
        val validity = Date(now.time + validityInMs)

        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun getEmail(token: String): String =
        Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body.subject

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
}
