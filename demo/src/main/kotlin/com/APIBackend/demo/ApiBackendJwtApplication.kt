package com.APIBackend.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class ApiBackendJwtApplication

fun main(args: Array<String>) {
	runApplication<ApiBackendJwtApplication>(*args)
}
