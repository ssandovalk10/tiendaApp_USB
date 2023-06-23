package co.edu.usbcali.tiendaApp.controller;

import co.edu.usbcali.tiendaApp.dto.CategoriaDTO;
import co.edu.usbcali.tiendaApp.request.ActualizarClienteRequest;
import co.edu.usbcali.tiendaApp.request.CrearClienteRequest;
import co.edu.usbcali.tiendaApp.response.ActualizarClienteResponse;
import co.edu.usbcali.tiendaApp.response.CrearClienteResponse;
import co.edu.usbcali.tiendaApp.response.ListarClientesResponse;
import co.edu.usbcali.tiendaApp.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/listar")
    List<ListarClientesResponse> listarTodos() {
        return clienteService.obtenerTodos();
    }

    @PostMapping("/nuevo")
    CrearClienteResponse nuevoCliente(@RequestBody @Valid CrearClienteRequest crearClienteRequest) throws Exception {
        return clienteService.crearCliente(crearClienteRequest);
    }

    @PutMapping(value = "/actualizar")
    ActualizarClienteResponse actualizarCliente(@RequestBody ActualizarClienteRequest actualizarClienteRequest) throws Exception {
        return clienteService.actualizarCliente(actualizarClienteRequest);
    }
}
