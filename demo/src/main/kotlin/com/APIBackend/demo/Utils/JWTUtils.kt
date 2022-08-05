package com.APIBackend.demo.Utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component

@Component
class JWTUtils {
    fun tokenGenerated (idUser: String): String =
        Jwts.builder()
            .setSubject(idUser)
            .signWith(SignatureAlgorithm.HS512, chaveDeSeguranca.toByteArray())
            .compact()

}