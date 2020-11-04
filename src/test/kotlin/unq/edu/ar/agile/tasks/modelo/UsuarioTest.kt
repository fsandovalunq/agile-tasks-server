package unq.edu.ar.agile.tasks.modelo

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test

class UsuarioTest {
    var usuario = Usuario()

    @Test
    fun usuarioTest() {
        usuario.password = "1234"
        usuario.userName = "admin"
        usuario.email = "email"

        assertEquals(usuario.userName, "admin")
        assertEquals(usuario.password, "1234")
        assertEquals(usuario.email, "email")
    }
}
