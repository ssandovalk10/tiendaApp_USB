package co.edu.usbcali.tiendaApp.controller;

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
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/nuevo")
    CrearProductoResponse nuevoProducto(@RequestBody @Valid CrearProductoRequest crearProductoRequest) throws Exception {
        return productoService.guardarNuevo(crearProductoRequest);
    }

}
