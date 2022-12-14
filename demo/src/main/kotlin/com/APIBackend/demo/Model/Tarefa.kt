package com.APIBackend.demo.Model

import com.fasterxml.jackson.annotation.JsonBackReference
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.Date
import javax.persistence.*


@Entity
data class Tarefa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    var nome: String,
    var prazo: String,
    var cadastro: String = LocalDate.now().toString(),
    var descricao: String,
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdUser")
    val users: Users?
)
