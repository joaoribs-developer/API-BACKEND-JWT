package com.APIBackend.demo.Repository

import com.APIBackend.demo.Model.Tarefa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TarefaRepository: JpaRepository<Tarefa, Int>