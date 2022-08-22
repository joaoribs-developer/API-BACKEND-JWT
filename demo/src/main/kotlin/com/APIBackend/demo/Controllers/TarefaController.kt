package com.APIBackend.demo.Controllers

import com.APIBackend.demo.Model.DTO.ErrorDTO
import com.APIBackend.demo.Model.DTO.SucessDTO
import com.APIBackend.demo.Model.Tarefa
import com.APIBackend.demo.Repository.TarefaRepository
import com.APIBackend.demo.Repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
@RestController
@RequestMapping("/api/tarefas")
class TarefaController(userRepository: UserRepository, val tarefaRepository: TarefaRepository) :
    BaseController(userRepository) {


    @PostMapping
    fun addTask(@RequestBody req: Tarefa, @RequestHeader("Authorization") authorization: String)
            : ResponseEntity<Any> {
        try {
            val user = readToken(authorization)
            val erros = mutableListOf<String>()
            if (req.nome.isNullOrEmpty() || req.nome.isNullOrBlank())
                erros.add("Adicione um nome à tarefa")
            if (req.descricao.isNullOrEmpty() || req.descricao.isNullOrBlank())
                erros.add("Adicione uma descrição à tarefa")
            if (erros.size > 0)
                return ResponseEntity(erros, HttpStatus.BAD_REQUEST)
            val tarefa = Tarefa(nome = req.nome, descricao = req.descricao, users = user, prazo = req.prazo)
            tarefaRepository.save(tarefa)
            return ResponseEntity(SucessDTO("Tarefa adiciona com sucesso"), HttpStatus.OK)
        } catch (e: Exception) {
            return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTask(
        @PathVariable id: Int,
        @RequestHeader("Authorization") authorization: String
    )
            : ResponseEntity<Any> {
        try {
            val user = readToken(authorization)
            val task = tarefaRepository.findByIdOrNull(id)
            return if (task == null || user.id != task.users?.id)
                ResponseEntity(ErrorDTO("Tarefa não encontrada"), HttpStatus.NOT_FOUND)
            else {
                this.tarefaRepository.deleteById(id)
                ResponseEntity(SucessDTO("Tarefa apagada com sucesso"), HttpStatus.OK)
            }
        } catch (e: Exception) {
            return ResponseEntity(
                ErrorDTO("Erro de acesso, tente novamente"), HttpStatus.INTERNAL_SERVER_ERROR
            )
        }
    }

    @PutMapping("/{id}")
    fun updateTask(
        @RequestBody req: Tarefa,
        @PathVariable id: Int,
        @RequestHeader("Authorization") authorization: String
    ):
            ResponseEntity<Any> {
        val user = readToken(authorization)
        try {

            val task = tarefaRepository.findByIdOrNull(id)
            if (task == null) {
                val newTask = Tarefa(nome = req.nome, descricao = req.descricao, users = user,prazo = req.prazo)
                this.tarefaRepository.save(newTask)
                return ResponseEntity(
                    ErrorDTO("Tarefa não encontrada,nova tarefa foi adicionada"),
                    HttpStatus.OK
                )
            } else if (id == task.id && user.id == task.users?.id) {
                task.nome = req.nome
                task.descricao = req.descricao
                task.prazo = req.prazo
                this.tarefaRepository.save(task)
                return ResponseEntity(SucessDTO("Tarefa atualizada com sucesso"), HttpStatus.OK)
            } else
                return ResponseEntity(
                    ErrorDTO("Impossivel atualizar, usuário não encontrado."),
                    HttpStatus.NOT_FOUND
                )


        } catch (e: Exception) {
            return ResponseEntity(
                ErrorDTO("Erro de acesso, tente novamente"),
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        }

    }

//    @GetMapping("/{id}")
//    fun getById(@RequestHeader("Authorization") authorization: String,
//                @PathVariable id: Int): ResponseEntity<Any> {
//        val user = readToken(authorization)
//        try {
//            val getId = tarefaRepository.findByIdOrNull(id)
//
//        }catch (e: Exception){
//            return ResponseEntity(
//                ErrorDTO("Erro de acesso, tente novamente"),
//                HttpStatus.INTERNAL_SERVER_ERROR
//            )
//        }
//
//
//        return ResponseEntity()
//    }

    @GetMapping
    fun getAll(@RequestHeader("Authorization") authorization: String)
    : ResponseEntity<Any>{
        try {
            val user = readToken(authorization)
            val task = tarefaRepository.findTarefaByUsers(user.id)
            return ResponseEntity(task, HttpStatus.OK)
        }catch (e:Exception){
            return ResponseEntity(e, HttpStatus.NOT_FOUND)
        }
    }
}