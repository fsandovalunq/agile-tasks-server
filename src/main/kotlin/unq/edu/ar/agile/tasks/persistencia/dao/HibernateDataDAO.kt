package Elementos.Ing.AgileTasks.persistencia.runner.dao

import unq.edu.ar.agile.tasks.persistencia.HibernateTransactionRunner
import unq.edu.ar.agile.tasks.persistencia.dao.DataDAO

open class HibernateDataDAO : DataDAO<Any?> {

    override fun clear() {
        val session = HibernateTransactionRunner.currentSession
        val nombreDeTablas = session.createNativeQuery("show tables").resultList
        session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate()
        nombreDeTablas.forEach { result ->
            var tabla = ""
            when(result){
                is String -> tabla = result
                is Array<*> -> tabla= result[0].toString()
            }
            session.createNativeQuery("truncate table $tabla").executeUpdate()
        }
        session.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate()
    }
}