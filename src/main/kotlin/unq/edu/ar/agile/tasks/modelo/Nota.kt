package unq.edu.ar.agile.tasks.modelo

import javax.persistence.*

@Entity
@Table(name = "notas")
class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id : Long? = null

    @Column(name = "titulo")
    var titulo: String? = null

    @Column(name = "descr")
    var descrpicion: String? = null

    @ManyToOne(optional = false)
    var user: Usuario? = null
}