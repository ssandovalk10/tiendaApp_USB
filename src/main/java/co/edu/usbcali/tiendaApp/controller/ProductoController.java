package co.edu.usbcali.tiendaApp.controller;

import co.edu.usbcali.tiendaApp.domain.Producto;
import co.edu.usbcali.tiendaApp.dto.ProductoDTO;
import co.edu.usbcali.tiendaApp.request.CrearProductoRequest;
import co.edu.usbcali.tiendaApp.response.CrearProductoResponse;
import co.edu.usbcali.tiendaApp.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/nuevoProducto")
    CrearProductoResponse nuevoProducto(@RequestBody @Valid CrearProductoRequest crearProductoRequest) throws Exception {
        return productoService.guardarNuevo(crearProductoRequest);
    }

    @PutMapping("/actualizarProducto")
    ResponseEntity<ProductoDTO> actualizarProducto(@RequestBody ProductoDTO productoDto ) throws Exception{
        return new ResponseEntity<ProductoDTO>(productoService.actualizar(productoDto),
                HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/")
    Producto buscarPorId(@RequestParam("id") Integer id) throws Exception {
        return productoService.buscarPorId(id);
    }

    @GetMapping("/buscarTodos")
    List<ProductoDTO> buscarTodos() {  return productoService.obtenerTodos(); }

    /**
     * Este metodo listarTodos, muestra la informacion completa de la llave foranea asociada,
     * en este caso mostrara toda la informacion de la categoria asociada al producto
     * */

    /*@GetMapping("/listarTodos")
    List<Producto> listarTodos() { return  productoService.listarTodos(); }*/

    @GetMapping(value = "/buscarPorNombre/")
    List<ProductoDTO> buscarPorNombre(@RequestParam("nombre") String nombre) throws Exception {
        return productoService.buscarPorNombreLike(nombre);
    }

}
