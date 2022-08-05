package com.APIBackend.demo.Utils

import org.apache.tomcat.websocket.BasicAuthenticator
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAutorization(authenticationManeger : AuthenticationManager, val jwtUtils: JWTUtils)
    : BasicAuthenticationFilter(authenticationManeger) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val autorization = request.getHeader(authorization)

        if (autorization!=null && authorization.startsWith(bearer)){
            val autorizado = getAuthentication(autorization)
        }
    }
}