package unq.edu.ar.agile.tasks.service.impl

import unq.edu.ar.agile.tasks.persistencia.HibernateTransactionRunner.runTrx
import unq.edu.ar.agile.tasks.modelo.Usuario
import unq.edu.ar.agile.tasks.service.UsuarioService
import unq.edu.ar.agile.tasks.persistencia.dao.UserDAO

class UsuarioServiceImpl: UsuarioService {
    private val UserDAO = UserDAO()
    override fun nuevoUsuario(user: Usuario): Usuario {
        return runTrx{
            user.id = UserDAO.getNextId()
            UserDAO.guardar(user)
            user
        }
    }

    override fun modificarUsuario(user: Usuario) {
        runTrx {
            UserDAO.actualizar(user)
        }
    }

    override fun eliminarUsuario(user: Usuario) {
        runTrx {
            UserDAO.eliminar(user)
        }
    }


    override fun getId(id: Long): Usuario {
        return runTrx {
            UserDAO.recuperarPorId(id)
        }
    }

    override fun validateUser(userName: String, password : String): Usuario? {
        return runTrx {
            UserDAO.validateUser(userName, password)
        }
    }

    override fun getUserByName(userName: String): Usuario {
        return runTrx {
            UserDAO.getUserByName(userName)
        }
    }
}