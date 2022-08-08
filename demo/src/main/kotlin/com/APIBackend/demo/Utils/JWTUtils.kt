package com.APIBackend.demo.Utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component

@Component
class JWTUtils {
    fun tokenGenerated (idUser: String): String =//gerador jwt
        Jwts.builder()
            .setSubject(idUser)
            .signWith(SignatureAlgorithm.HS512, chaveDeSeguranca.toByteArray())
            .compact()

    fun tokenValidation(token :String):Boolean{
        //validar se é token valido
       val claims = getClaimsToken(token)
         if (claims!= null){
            val idUser = claims.subject
            if (!idUser.isNullOrEmpty() && !idUser.isNullOrBlank()){
              return  true
            }
        }
        return false

    }

    private fun getClaimsToken(token: String) =
        //pegar os claims do token, e retornar o corpo do token
         try {
            Jwts.parser().setSigningKey(chaveDeSeguranca.toByteArray()).parseClaimsJws(token).body
        }catch (e: Exception){
            null
        }

    fun getUserID(token: String): String?{
        val claims = getClaimsToken(token)
        return claims?.subject
    }

    }


