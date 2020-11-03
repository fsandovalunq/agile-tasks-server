package unq.edu.ar.agile.tasks.modelo


import javax.persistence.*

@Entity
@Table(name = "usuarios")
class Usuario {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id : Long? = null

    @Id
    @Column(name = "username")
    var userName: String? = null

    @Column(name = "pass")
    var password: String? = null

    @Column(name = "email")
    var email: String? = null
}
