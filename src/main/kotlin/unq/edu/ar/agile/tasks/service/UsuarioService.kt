package unq.edu.ar.agile.tasks.service

import unq.edu.ar.agile.tasks.modelo.Usuario

interface UsuarioService {
    fun nuevoUsuario(user: Usuario): Usuario
    fun modificarUsuario(user: Usuario)
    fun eliminarUsuario(user: Usuario)
    fun getId(id : Int): Usuario
    fun validateUser(userName: String, password: String): Usuario?
    fun getUserByName(userName: String): Usuario
}