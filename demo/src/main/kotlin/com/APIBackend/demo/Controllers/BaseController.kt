package com.APIBackend.demo.Controllers

import com.APIBackend.demo.Model.Users
import com.APIBackend.demo.Repository.UserRepository
import com.APIBackend.demo.Utils.JWTUtils
import org.springframework.data.repository.findByIdOrNull

open class BaseController(val userRepository: UserRepository) {
    fun readToken(authorization :String):Users{
    val token =  authorization.substring(7)
    val userIdString: String = JWTUtils().getUserID(token)
        ?: throw IllegalAccessException("Você não tem acesso à este recurso")
    val user = userRepository.findByIdOrNull(userIdString.toInt())
        ?:throw IllegalAccessException("Você não tem acesso à este recurso")
        return user
    }
}