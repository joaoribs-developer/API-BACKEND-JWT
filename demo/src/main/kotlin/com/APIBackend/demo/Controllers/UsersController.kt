package com.APIBackend.demo.Controllers

import com.APIBackend.demo.Repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/usuario")
class UsersController(val userRepository: UserRepository) {
    @GetMapping
    fun getUser() = this.userRepository.findAll()
}