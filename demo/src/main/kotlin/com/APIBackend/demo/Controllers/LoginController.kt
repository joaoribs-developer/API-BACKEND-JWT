package com.APIBackend.demo.Controllers

import com.APIBackend.demo.Model.DTO.LoginDTO
import com.APIBackend.demo.Model.LoginResponse
import com.APIBackend.demo.Model.Users
import com.APIBackend.demo.Repository.UserRepository
import com.APIBackend.demo.Utils.ErrorLogin
import com.APIBackend.demo.Utils.JWTUtils
import com.APIBackend.demo.Utils.md5
import com.APIBackend.demo.Utils.toHex
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import javax.management.RuntimeErrorException

@RestController//dizendo pro spring que este é um controller
@RequestMapping("/api/login")//rota que estará o login
class LoginController(val userRepository: UserRepository) {

    @GetMapping
    fun sayHello() = this.userRepository.findAll()
    @PostMapping
    fun loginUser(@RequestBody dto: LoginDTO): ResponseEntity<Any>{
        try {
            if (dto == null || dto.login.isNullOrBlank() || dto.login.isNullOrEmpty()
                || dto.senha.isNullOrBlank() || dto.senha.isNullOrEmpty()){
                return ResponseEntity(ErrorLogin(HttpStatus.INTERNAL_SERVER_ERROR,"Login ou senha inválidos")
                    ,HttpStatus.INTERNAL_SERVER_ERROR)
            }
            var users = userRepository.findByLogin(dto.login)
            if (users?.senha != md5(dto.senha).toHex()) return ResponseEntity(ErrorLogin(HttpStatus.INTERNAL_SERVER_ERROR,"Login ou senha inválidos")
                ,HttpStatus.INTERNAL_SERVER_ERROR)
            val token = JWTUtils().tokenGenerated(users.id.toString())
            val response = LoginResponse(users.nome,users.login, token)





            return ResponseEntity(response, HttpStatus.OK)
        }
        catch (e: Exception){
            return ResponseEntity(ErrorLogin(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de serviço, tente novamente"),
                HttpStatus.INTERNAL_SERVER_ERROR)

        }


    }
}