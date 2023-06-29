package co.edu.usbcali.tiendaApp.controller;

import co.edu.usbcali.tiendaApp.domain.Categoria;
import co.edu.usbcali.tiendaApp.dto.CategoriaDTO;
import co.edu.usbcali.tiendaApp.repository.CategoriaRepository;
import co.edu.usbcali.tiendaApp.request.CrearCategoriaRequest;
import co.edu.usbcali.tiendaApp.response.CrearCategoriaResponse;
import co.edu.usbcali.tiendaApp.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaRepository categoriaRepository, CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    @PostMapping("/nuevaCategoria")
    CrearCategoriaResponse nuevaCategoria(@RequestBody @Valid CrearCategoriaRequest crearCategoriaRequest) throws Exception {
        return categoriaService.crearCategoria(crearCategoriaRequest);
    }

    @PutMapping("/actualizarCategoria")
    ResponseEntity<CategoriaDTO> actualizarCategoria(@RequestBody CategoriaDTO categoriaDto ) throws Exception{
        return new ResponseEntity<CategoriaDTO>(categoriaService.actualizar(categoriaDto),
                HttpStatus.OK);
    }
    @GetMapping("/buscarTodas")
    List<CategoriaDTO> buscarTodas() {
        return categoriaService.obtenerTodos();
    }


    @GetMapping("/buscarPorId/")
    ResponseEntity<CategoriaDTO> buscarPorId(@RequestParam("id") Integer id) throws Exception {
        return new ResponseEntity<CategoriaDTO>(categoriaService.buscarPorId(id),
                HttpStatus.OK);
    }
    @DeleteMapping("eliminar/{id}")
    public void eliminarCategoria(@RequestParam Integer id)  throws Exception  {
        categoriaService.eliminarById(id);
    }

}
