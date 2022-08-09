package com.APIBackend.demo.Model

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*


@Entity
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val nome: String,
    val login :String,
    var senha :String,
    @JsonBackReference
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    var tarefa: List<Tarefa?> = emptyList()
    )
