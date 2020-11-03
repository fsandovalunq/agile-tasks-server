package services.impl

import Elementos.Ing.AgileTasks.persistencia.runner.dao.NotaDAO
import unq.edu.ar.agile.tasks.persistencia.HibernateTransactionRunner.runTrx
import services.NotaService
import unq.edu.ar.agile.tasks.modelo.Nota

class NotaServiceImpl: NotaService {
    val notaDAO: NotaDAO = NotaDAO()
    override fun agregarNota(nota: Nota) {
        runTrx{
            notaDAO.guardar(nota)
        }
    }

    override fun recuperarPorId(id: Long): Nota {
       return runTrx {
            notaDAO.recuperar(id)
        }
    }

    override fun recuperarPorUserId(id: Long): List<Nota> {
        return runTrx {
            notaDAO.recuperarPorUserId(id)
        }
    }

    override fun modificarNota(nota: Nota) {
        runTrx {
            notaDAO.actualizar(nota)
        }
    }
    override fun asignarNotaATarea(nota: Nota) {
        TODO("Not yet implemented")
    }

    override fun recuperarTodas(): List<Nota> {
        var ret: List<Nota> = emptyList()
        runTrx {
            ret = notaDAO.recuperarATodos()
        }
        return ret
    }

    override fun eliminar(nota : Nota) {
       runTrx {
           notaDAO.eliminar(nota)
       }
    }

     override fun recuperarPorUserName(userName: String): List<Nota> {
      return runTrx {
             notaDAO.recuperarPorUserName(userName)
       }
    }

}