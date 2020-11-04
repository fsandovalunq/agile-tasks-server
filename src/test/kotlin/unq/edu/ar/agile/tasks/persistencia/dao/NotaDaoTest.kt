package unq.edu.ar.agile.tasks.persistencia.dao

import unq.edu.ar.agile.tasks.excepciones.NotFoundException
import Elementos.Ing.AgileTasks.persistencia.runner.dao.NotaDAO
import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import unq.edu.ar.agile.tasks.modelo.Nota
import unq.edu.ar.agile.tasks.modelo.Usuario
import unq.edu.ar.agile.tasks.persistencia.HibernateTransactionRunner.runTrx
import unq.edu.ar.agile.tasks.service.impl.UsuarioServiceImpl
import unq.edu.ar.agile.tasks.utils.DataServiceImpl


class NotaDAOTest {

    val notaDAO = NotaDAO()
    val nota = Nota()
    var usuario = Usuario()
    val dataService : DataServiceImpl = DataServiceImpl()

    @BeforeEach
    fun before(){

        usuario.password = "1234"
        usuario.userName = "name"

        nota.titulo = "titulo"
        nota.descrpicion = "descripcion"
        nota.user = usuario

        usuario = UsuarioServiceImpl().nuevoUsuario(usuario)
        runTrx {
            notaDAO.guardar(nota)
        }
    }

    @Test
    fun guardarTest(){

        val nuevaNota = Nota()

        nuevaNota.titulo = "titulo"
        nuevaNota.descrpicion = "descripcion"
        nuevaNota.user = usuario

        val notaRecuperada = runTrx{
            notaDAO.guardar(nuevaNota)
            notaDAO.recuperar(nuevaNota.id!!)
        }

        Assert.assertEquals(notaRecuperada.descrpicion, nuevaNota.descrpicion)
        Assert.assertEquals(notaRecuperada.titulo, nuevaNota.titulo)
        Assert.assertEquals(notaRecuperada.id, nuevaNota.id)
    }

    @Test
    fun recuperarTest(){
        val notaRecuperada = runTrx{
            notaDAO.recuperar(nota.id!!)
        }

        Assert.assertEquals(notaRecuperada.descrpicion, nota.descrpicion)
        Assert.assertEquals(notaRecuperada.titulo, nota.titulo)
        Assert.assertEquals(notaRecuperada.id, nota.id)
    }

    @Test
    fun actualizarTest(){
        nota.descrpicion = "nuevaDescripcion"
        nota.titulo = "nuevoTitulo"

        val notaRecuperada = runTrx{
            notaDAO.actualizar(nota)
            notaDAO.recuperar(nota.id!!)
        }

        Assert.assertEquals(notaRecuperada.descrpicion, nota.descrpicion)
        Assert.assertEquals(notaRecuperada.titulo, nota.titulo)
        Assert.assertEquals(notaRecuperada.id, nota.id)
    }

    @Test
    fun eliminarTest(){
        runTrx {
            notaDAO.eliminar(nota)
        }

        Assertions.assertThrows(NotFoundException::class.java){
            runTrx {
                notaDAO.recuperar(nota.id!!)
            }
        }
    }

    @Test
    fun recuperarTodoTest(){
        val notas = runTrx{
            notaDAO.recuperarATodos()
        }

        Assert.assertEquals(notas.size, 1)

        Assert.assertEquals(notas[0].descrpicion, nota.descrpicion)
        Assert.assertEquals(notas[0].titulo, nota.titulo)
        Assert.assertEquals(notas[0].id, nota.id)
    }

    @Test
    fun recuperarPorUserIdTest(){
        val notas = runTrx {
            notaDAO.recuperarPorUserId(usuario.id!!)
        }

        Assert.assertEquals(notas.size, 1)
        Assert.assertEquals(notas[0].descrpicion, nota.descrpicion)
        Assert.assertEquals(notas[0].titulo, nota.titulo)
        Assert.assertEquals(notas[0].id, nota.id)
    }

    @AfterEach
    fun after(){
        dataService.deleteAll()
    }
}