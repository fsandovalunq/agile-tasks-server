package services

import unq.edu.ar.agile.tasks.modelo.Nota

interface NotaService {
    fun agregarNota(nota: Nota)
    fun recuperarPorId(id: Long): Nota
    fun recuperarPorUserId(id : Long) : List<Nota>
    fun modificarNota(nota: Nota)
    fun asignarNotaATarea(nota: Nota)
    fun recuperarTodas() : List<Nota>
    fun eliminar(nota : Nota)
    fun recuperarPorUserName(string: String): List<Nota>
}