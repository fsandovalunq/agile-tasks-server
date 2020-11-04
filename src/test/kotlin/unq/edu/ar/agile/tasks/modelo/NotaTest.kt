package unq.edu.ar.agile.tasks.modelo

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test

class NotaTest {
    var nota = Nota()
    var usuario = Usuario()

    @Test
    fun notaTest() {
        nota.descrpicion = "Descripcion"
        nota.titulo ="Titulo"
        nota.user = usuario

        assertEquals(nota.descrpicion, "Descripcion")
        assertEquals(nota.titulo, "Titulo")
    }
}
