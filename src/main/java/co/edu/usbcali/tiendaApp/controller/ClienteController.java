package co.edu.usbcali.tiendaApp.controller;

import co.edu.usbcali.tiendaApp.request.CrearClienteRequest;
import co.edu.usbcali.tiendaApp.response.CrearClienteResponse;
import co.edu.usbcali.tiendaApp.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/nuevo")
    CrearClienteResponse nuevoCliente(@RequestBody @Valid CrearClienteRequest crearClienteRequest) throws Exception {
        return clienteService.crearCliente(crearClienteRequest);
    }
}
