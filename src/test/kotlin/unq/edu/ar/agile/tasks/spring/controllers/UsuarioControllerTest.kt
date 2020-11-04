package unq.edu.ar.agile.tasks.spring.controllers

import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import unq.edu.ar.agile.tasks.modelo.Usuario
import unq.edu.ar.agile.tasks.utils.DataServiceImpl
import javax.persistence.NoResultException

class UsuarioControllerTest {

    val userController = UsuarioController()
    val usuario = Usuario()

    @BeforeEach
    fun before(){
        usuario.password = "1234"
        usuario.userName = "name"

        userController.agregarUsario(usuario)
    }

    @Test
    fun agregarUsuarioTest(){
        val nuevoUsuario = Usuario()
        nuevoUsuario.password = "1234"
        nuevoUsuario.userName = "admin"

        userController.agregarUsario(nuevoUsuario)

        val usuarioRecuperado = userController.buscarUsuarioPorId(nuevoUsuario.id!!)

        Assert.assertEquals(nuevoUsuario.id, usuarioRecuperado.id)
        Assert.assertEquals(nuevoUsuario.password, usuarioRecuperado.password)
        Assert.assertEquals(nuevoUsuario.userName, usuarioRecuperado.userName)

    }

    @Test
    fun actualizarUsuarioTest(){
        val nuevoUsuario = Usuario()
        nuevoUsuario.password = "otraPassword"
        nuevoUsuario.userName = "otroUserName"

        userController.ModificarUser(usuario.userName!!, nuevoUsuario)

        val usuarioRecuperado = userController.buscarUsuarioPorId(usuario.id!!)

        Assert.assertEquals(usuarioRecuperado.id, usuarioRecuperado.id)
        Assert.assertEquals(usuarioRecuperado.password, usuarioRecuperado.password)
        Assert.assertEquals(usuarioRecuperado.userName, usuarioRecuperado.userName)
    }

    @Test
    fun eliminarUsuarioTest(){
        userController.deleteUser(usuario.id!!)

        Assertions.assertThrows(NoResultException::class.java){
            userController.buscarUsuarioPorId(usuario.id!!)
        }
    }

    @Test
    fun validarUsuarioTest(){
        val validacion = UsuarioValidacion(usuario.userName!!, usuario.password!!)
        Assert.assertEquals(userController.validarUsuario(validacion)!!
                .userName, usuario.userName)
        Assertions.assertThrows(NoResultException::class.java){
            userController.validarUsuario(UsuarioValidacion("",""))
        }
    }

    @Test
    fun recuperarPorIdTest(){
        val usuarioRecuperado = userController.buscarUsuarioPorId(usuario.id!!)

        Assert.assertEquals(usuario.id, usuarioRecuperado.id)
        Assert.assertEquals(usuario.password, usuarioRecuperado.password)
        Assert.assertEquals(usuario.userName, usuarioRecuperado.userName)

        Assertions.assertThrows(NoResultException::class.java){
            userController.buscarUsuarioPorId(-1)
        }
    }

    @AfterEach
    fun after(){
        DataServiceImpl().deleteAll()
    }
}