package unq.edu.ar.agile.tasks.utils

import Elementos.Ing.AgileTasks.persistencia.runner.dao.HibernateDataDAO
import Elementos.Ing.AgileTasks.persistencia.runner.dao.NotaDAO
import unq.edu.ar.agile.tasks.modelo.Nota
import unq.edu.ar.agile.tasks.modelo.Usuario
import unq.edu.ar.agile.tasks.persistencia.HibernateTransactionRunner.runTrx
import unq.edu.ar.agile.tasks.persistencia.dao.UserDAO

class DataServiceImpl : DataService{
    var NotaDAO: NotaDAO = NotaDAO()
    var UserDAO: UserDAO = UserDAO()
    val dataDAO = HibernateDataDAO()

    override fun crearDatosDummy(){
        this.crearUserAdmin()
        var nota1 = Nota()
        nota1.user = UserDAO.recuperar("admin")
        nota1.titulo = "Dummy1"
        nota1.descrpicion = "Test de Nota"
        NotaDAO.guardar(nota1)
    }
    fun crearUserAdmin(){
        val user = Usuario()
        user.password = "quick"
        user.userName = "admin"
        UserDAO.guardar(user)
    }

    override fun deleteAll() {
        runTrx {
            dataDAO.clear()
        }
    }
}