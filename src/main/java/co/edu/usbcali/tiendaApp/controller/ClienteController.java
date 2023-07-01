package co.edu.usbcali.tiendaApp.controller;

import co.edu.usbcali.tiendaApp.domain.Cliente;
import co.edu.usbcali.tiendaApp.dto.ClienteDTO;
import co.edu.usbcali.tiendaApp.dto.ProductoDTO;
import co.edu.usbcali.tiendaApp.request.CrearClienteRequest;
import co.edu.usbcali.tiendaApp.response.CrearClienteResponse;
import co.edu.usbcali.tiendaApp.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/nuevoCliente")
    CrearClienteResponse nuevoCliente(@RequestBody @Valid CrearClienteRequest crearClienteRequest) throws Exception {
        return clienteService.crearCliente(crearClienteRequest);
    }

    @PutMapping("/actualizarCliente")
    ResponseEntity<ClienteDTO> actualizarCliente(@RequestBody ClienteDTO clienteDTO ) throws Exception{
        return new ResponseEntity<ClienteDTO>(clienteService.actualizar(clienteDTO),
                HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorId/")
    Cliente buscarPorId(@RequestParam("id") Integer id) throws Exception {
        return clienteService.buscarClientePorId(id);
    }

    @GetMapping("/buscarTodos")
    List<ClienteDTO> buscarTodos() {  return clienteService.obtenerTodos(); }

    @GetMapping(value = "/buscarPorNombre/")
    List<ClienteDTO> buscarPorNombre(@RequestParam("nombres") String nombres) throws Exception {
        return clienteService.buscarPorNombresLike(nombres);
    }

}
