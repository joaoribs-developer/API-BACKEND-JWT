package com.APIBackend.demo.Controllers

import com.APIBackend.demo.Model.LoginResponse
import com.APIBackend.demo.Model.Users
import com.APIBackend.demo.Repository.UserRepository
import com.APIBackend.demo.Utils.ErrorLogin
import com.APIBackend.demo.Utils.JWTUtils
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
class LoginController() {

    @GetMapping
    fun sayHello() = "Hello World!"
    @PostMapping
    fun loginUser(@RequestBody users: Users): ResponseEntity<Any>{
        try {
            if (users == null || users.login.isNullOrBlank() || users.login.isNullOrEmpty()
                || users.senha.isNullOrBlank() || users.senha.isNullOrEmpty()){
                return ResponseEntity(ErrorLogin(HttpStatus.INTERNAL_SERVER_ERROR,"Login ou senha inválidos")
                    ,HttpStatus.INTERNAL_SERVER_ERROR)
            }

            val idUser = users.id
            val token = JWTUtils().tokenGenerated(idUser.toString())
            val response = LoginResponse("João Vitor","joão@teste.com", token)





            return ResponseEntity(response, HttpStatus.OK)
        }
        catch (e: Exception){
            return ResponseEntity(ErrorLogin(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de serviço, tente novamente"),
                HttpStatus.INTERNAL_SERVER_ERROR)

        }


    }
}