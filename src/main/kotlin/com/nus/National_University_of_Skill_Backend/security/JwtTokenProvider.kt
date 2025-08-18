package com.nus.National_University_of_Skill_Backend.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider {
    @Value("\${jwt.secret}")
    private lateinit var secretKey: String

    @Value("\${jwt.expiration}")
    private var validityInMs: Long = 0


    private val accessTokenValidity: Long = 15 * 60 * 1000 // 15 minutes
    private val refreshTokenValidity: Long = 30L * 24 * 60 * 60 * 1000 // 30 day's


    fun generateAccessToken(email: String): String {
        val now = Date()
//        val validity = Date(now.time + validityInMs)
        val expiry = Date(now.time + accessTokenValidity)
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun generateRefreshToken(email: String): String {
        val now = Date()
        val expiry = Date(now.time + refreshTokenValidity)
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .claim("type", "refresh")
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
