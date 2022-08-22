package com.APIBackend.demo.Repository

import com.APIBackend.demo.Model.Tarefa
import com.APIBackend.demo.Model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
@Repository
interface TarefaRepository: JpaRepository<Tarefa, Int>{

}
