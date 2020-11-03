package unq.edu.ar.agile.tasks.persistencia.dao

import Elementos.Ing.AgileTasks.excepciones.DuplicatedTypeException
import Elementos.Ing.AgileTasks.excepciones.NotFoundException
import unq.edu.ar.agile.tasks.persistencia.HibernateTransactionRunner
import java.sql.SQLIntegrityConstraintViolationException
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery



open class HibernateDAO<T>(private val entityType: Class<T>) {

    fun guardar(item: T) {
        val session = HibernateTransactionRunner.currentSession
        try {
            session.save(item)
        }catch (e: Exception){
            var cause = e.cause
            when(cause){
                is SQLIntegrityConstraintViolationException ->
                    throw DuplicatedTypeException("Tipo duplicado")
                else ->
                    throw e
            }
        }
    }

    fun recuperar(id: String): T {
        val session = HibernateTransactionRunner.currentSession
        return session.get(entityType, id) ?: throw NotFoundException("Id no encontrado para Clase: " + this.entityType)
    }
    fun recuperar(id: Long): T {
        val session = HibernateTransactionRunner.currentSession
        return session.get(entityType, id) ?: throw NotFoundException("Id no encontrado para Clase: " + this.entityType)
    }

    fun actualizar(item: T) {
        val session = HibernateTransactionRunner.currentSession
        session.update(item)
    }

    fun recuperarATodos(): List<T> {
        val session = HibernateTransactionRunner.currentSession
        val builder: CriteriaBuilder = session.criteriaBuilder
        val criteria: CriteriaQuery<T> = builder.createQuery(entityType)
        criteria.from(entityType)
        return session.createQuery(criteria).resultList
    }

    fun eliminar(item: T){
        val session = HibernateTransactionRunner.currentSession
        session.delete(item)
    }


}