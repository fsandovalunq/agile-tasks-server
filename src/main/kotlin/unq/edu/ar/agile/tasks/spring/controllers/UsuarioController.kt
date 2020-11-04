package unq.edu.ar.agile.tasks.spring.controllers

import org.springframework.web.bind.annotation.*
import unq.edu.ar.agile.tasks.modelo.Usuario
import unq.edu.ar.agile.tasks.service.impl.UsuarioServiceImpl

@RestController
@CrossOrigin(origins= ["http://localhost:3000"])
@RequestMapping("/users")
class UsuarioController() {
    private val usuarioServiceImpl: UsuarioServiceImpl = UsuarioServiceImpl()

    @PostMapping("/addUser")
    fun agregarUsario(@RequestBody usuario: Usuario) {
        usuarioServiceImpl.nuevoUsuario(usuario)
    }

    @PutMapping("/modificarUser/{userName}")
    fun ModificarUser(@PathVariable userName: String, @RequestBody userNuevo : Usuario) {
        val userViejo : Usuario = buscarUsuarioPorUserName(userName)
        userViejo.email = userNuevo.email
        userViejo.password = userNuevo.password

        usuarioServiceImpl.modificarUsuario(userViejo)
    }

    @GetMapping("/userById/{id}")
    fun buscarUsuarioPorId(@PathVariable id: Long): Usuario {
        return usuarioServiceImpl.getId(id)
    }

    @GetMapping("/userByUserName/{userName}")
    fun buscarUsuarioPorUserName(@PathVariable userName : String) : Usuario {
        return usuarioServiceImpl.getUserByName(userName)
    }

    @PostMapping("/DeleteUser/{id}")
    fun deleteUser(@PathVariable id: Long) {
        val usuario : Usuario = buscarUsuarioPorId(id)
        usuarioServiceImpl.eliminarUsuario(usuario)
    }

    @PostMapping("/validateUser")
    fun validarUsuario(@RequestBody usuario: UsuarioValidacion): Usuario? {
        val usuarioObtenido = usuarioServiceImpl.validateUser(usuario.userName, usuario.password)
        if ( usuarioObtenido != null) {
            return usuarioObtenido
        }
        else {throw Exception("No existe este usuario")}
    }

}

data class UsuarioValidacion(val userName: String, val password: String)
