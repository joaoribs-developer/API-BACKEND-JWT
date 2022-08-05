package com.APIBackend.demo.Repository

import com.APIBackend.demo.Model.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<Users, Int>