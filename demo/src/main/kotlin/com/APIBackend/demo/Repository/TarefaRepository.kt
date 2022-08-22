package com.APIBackend.demo.Repository

import com.APIBackend.demo.Model.Tarefa
import com.APIBackend.demo.Model.Users
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
@ComponentScan(basePackages = ["com.APIBackend.demo"])
@Repository
interface TarefaRepository: JpaRepository<Tarefa, Int>{
    @Query("SELECT t FROM Tarefa t WHERE t.users.id = :id")
    fun findTarefaByUsers(id: Int): List<Tarefa?>

}
