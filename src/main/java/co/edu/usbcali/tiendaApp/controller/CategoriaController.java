package co.edu.usbcali.tiendaApp.controller;

import co.edu.usbcali.tiendaApp.domain.Categoria;
import co.edu.usbcali.tiendaApp.dto.CategoriaDTO;
import co.edu.usbcali.tiendaApp.repository.CategoriaRepository;
import co.edu.usbcali.tiendaApp.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaRepository categoriaRepository, CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    @PostMapping(value = "/nueva")
    CategoriaDTO nuevaCategoria(@RequestBody CategoriaDTO categoriaDTO) throws Exception {
        return categoriaService.guardar(categoriaDTO);
    }

    @GetMapping(value = "/buscarTodas")
    List<CategoriaDTO> buscarTodas() {
        return categoriaService.obtenerTodos();
    }

    @PutMapping(value = "/actualizar")
    CategoriaDTO actualizarCategoria(@RequestBody CategoriaDTO categoriaDTO) throws Exception {
        return categoriaService.actualizar(categoriaDTO);
    }

    @GetMapping(value = "/buscarPorNombre")
    List<CategoriaDTO> buscarPorNombre(@RequestParam("nombre") String nombre) throws Exception {
        return categoriaService.buscarPorNombreLike(nombre);
    }

    @DeleteMapping("eliminar/{id}")
    public void eliminarCategoria(@PathVariable Integer id)  throws Exception  {
        categoriaService.eliminarById(id);
    }

}
