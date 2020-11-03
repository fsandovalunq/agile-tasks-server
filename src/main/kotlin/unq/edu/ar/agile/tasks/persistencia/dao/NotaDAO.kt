package Elementos.Ing.AgileTasks.persistencia.runner.dao

import unq.edu.ar.agile.tasks.persistencia.HibernateTransactionRunner
import unq.edu.ar.agile.tasks.modelo.Nota
import unq.edu.ar.agile.tasks.persistencia.dao.HibernateDAO


class NotaDAO  : HibernateDAO<Nota>(Nota::class.java) {
    fun recuperarPorUserId(id: Long) : List<Nota> {
            val session = HibernateTransactionRunner.currentSession
            val hql = "select u from Nota u where u.user.id = :pId"
            val query = session.createQuery(hql, Nota::class.java)
            query.setParameter("pId", id)
            return query.list()
     }

     fun recuperarPorUserName(userName: String) : List<Nota> {
         val session = HibernateTransactionRunner.currentSession
         val hql = "select u from Nota u where lower(u.user.userName) = :puserName"
         val query = session.createQuery(hql, Nota::class.java)
         query.setParameter("puserName", userName.toLowerCase())
         return query.list()
     }
 }