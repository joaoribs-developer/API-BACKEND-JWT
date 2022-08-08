package com.APIBackend.demo.Repository

import com.APIBackend.demo.Model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<Users, Int>{
    fun findByLogin(login: String): Users?
}
