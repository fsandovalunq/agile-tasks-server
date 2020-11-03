package unq.edu.ar.agile.tasks.spring.controllers

import org.springframework.web.bind.annotation.*
import services.impl.NotaServiceImpl
import unq.edu.ar.agile.tasks.modelo.Nota

@RestController
@CrossOrigin(origins= ["http://localhost:3000"])
@RequestMapping(value = ["/notes"])
class NotaController() {
    private val notaService: NotaServiceImpl = NotaServiceImpl()

    @RequestMapping(value = ["/allnotes"], method = [(RequestMethod.GET)])
    fun getAllNotas(): List<Nota> = notaService.recuperarTodas()

    //Recupera una nota por Id de la nota
    @GetMapping("/get/{id}")
    fun getNotaId(@PathVariable id: Long): Nota {
        return notaService.recuperarPorId(id)
    }

    //Recupera todas las notas por Id del usuario
    @GetMapping("/getByUser/{id}")
    fun buscarPorUsuario(@PathVariable id: Long): List<Nota> {
        return notaService.recuperarPorUserId(id)
    }


    //Te permite agregar una nota nueva
    @PostMapping("/NuevaNota")
    fun agregarNota(@RequestBody nota: Nota) {
        notaService.agregarNota(nota)
    }

    //Te permite eliminar una nota
    @PostMapping("/DeleteNota/{id}")
    fun borrarNota(@PathVariable id: Long) {
        val nota: Nota = this.getNotaId(id)

        notaService.eliminar(nota)
    }

    //Buscar por userName
    @GetMapping("/getByUserName/{userName}")
    fun buscarPorNombreUsuario(@PathVariable userName : String) : List<Nota> {
        return notaService.recuperarPorUserName(userName)
    }

    //Editar notas
    @PutMapping("/EditarNota/{id}")
    fun editarNota(@PathVariable id: Long, @RequestBody nota: Nota) {
        var notaVieja: Nota = getNotaId(id)
        notaVieja.titulo = nota.titulo
        notaVieja.descrpicion = nota.descrpicion
        notaService.modificarNota(notaVieja)
    }
}

