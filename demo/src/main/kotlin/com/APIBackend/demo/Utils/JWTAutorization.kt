package com.APIBackend.demo.Utils

import com.APIBackend.demo.Model.Users
import org.apache.tomcat.websocket.BasicAuthenticator
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAutorization(authenticationManeger : AuthenticationManager, val jwtUtils: JWTUtils)
    : BasicAuthenticationFilter(authenticationManeger) {//filter e validador de cada token

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val autorizacao = request.getHeader(authorization)
        if (autorizacao!=null && autorizacao.startsWith(bearer)){//VERIFICAR se autorização não é nula e se começa com o bearer(metodo de passagem do jwt)
            val autorizado = getAuthentication(autorizacao)
            //caso seja autorizado
            SecurityContextHolder.getContext().authentication = autorizado
        }
        chain.doFilter(request, response)
    }

    private fun getAuthentication(autorizacao: String): UsernamePasswordAuthenticationToken {
    val token = autorizacao.substring(7)
        val idUser = jwtUtils.getUserID(token)
    if(!idUser.isNullOrEmpty() && !idUser.isNullOrBlank()){
    val user = Users(idUser.toInt(), "joão vitor","joao@teste.com", "joaosenha")
    val userIMPL = UserDetails(user)
    return UsernamePasswordAuthenticationToken(userIMPL, null, userIMPL.authorities)
    }
    throw UsernameNotFoundException("Token inválido")
    }
}