package com.APIBackend.demo.Utils

import org.springframework.http.HttpStatus

data class ErrorLogin(val status: HttpStatus, val error: String)