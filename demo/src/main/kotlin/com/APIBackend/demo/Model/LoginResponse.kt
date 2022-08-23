package com.APIBackend.demo.Model

import org.springframework.http.HttpStatus

data class LoginResponse(val nome: String, val email: String, val token: String="")
