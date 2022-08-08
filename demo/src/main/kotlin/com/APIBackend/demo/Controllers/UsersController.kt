package com.APIBackend.demo.Controllers

import com.APIBackend.demo.Model.Users
import com.APIBackend.demo.Repository.UserRepository
import com.APIBackend.demo.Utils.ErrorLogin
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.management.RuntimeErrorException

@RestController
@RequestMapping("api/cadastro")
class UsersController(val userRepository: UserRepository) {
    @GetMapping
    fun getUser() = this.userRepository.findAll()

    @PostMapping
    fun addUser(@RequestBody users: Users): ResponseEntity<Any>{
        val erros = mutableListOf<String>()
        val teste = userRepository.findByLogin(users.login)


        try {
            when{
            users.nome.isNullOrEmpty()
                    || users.nome.isNullOrBlank()
                    || users.nome.length < 3 -> erros.add("Nome inválido")
            users.login.isNullOrEmpty()
                    || users.login.isNullOrBlank()
                    || users.login.length <5 -> erros.add("Login inválido")
            users.senha.isNullOrEmpty()
                    || users.senha.isNullOrBlank()
                    || users.senha.length <4 -> erros.add("Senha inválida")
                else -> ResponseEntity(ErrorLogin(HttpStatus.BAD_REQUEST, "$erros"), HttpStatus.BAD_REQUEST)
            }
            userRepository.save(users)
            return ResponseEntity(users, HttpStatus.OK)
        }catch (e:Exception){
            return ResponseEntity(ErrorLogin(HttpStatus.INTERNAL_SERVER_ERROR, "Erro de serviço tente novamente"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}