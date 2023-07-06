package co.edu.usbcali.tiendaApp.controller;

import co.edu.usbcali.tiendaApp.domain.Pedido;
import co.edu.usbcali.tiendaApp.domain.Producto;
import co.edu.usbcali.tiendaApp.dto.PedidoDTO;
import co.edu.usbcali.tiendaApp.dto.ProductoDTO;
import co.edu.usbcali.tiendaApp.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/buscarTodos")
    List<PedidoDTO> buscarTodos() {
        return pedidoService.obtenerTodos();
    }

    @GetMapping(value = "/buscarPorId/")
    Pedido buscarPorId(@RequestParam("id") Integer id) throws Exception {
        return pedidoService.buscarPorId(id);
    }

    @GetMapping(value = "/buscarPorClienteId/")
    List<Pedido> buscarPorClienteId(@RequestParam("id") Integer id) {
        return pedidoService.buscarPorClienteId(id);
    }

    @GetMapping(value = "/buscarPorEstadoPedidoId/")
    List<Pedido> buscarPorEstadoPedidoId(@RequestParam("id") Integer id) {
        return pedidoService.buscarPorEstadoPedidoId(id);
    }

    @PostMapping("/nuevaPedido")
    PedidoDTO nuevaPedido(@RequestBody @Valid PedidoDTO pedidoDTO) throws Exception {
        return pedidoService.guardar(pedidoDTO);
    }

    @PutMapping("/actualizarPedido")
    ResponseEntity<PedidoDTO> actualizarPedido(@RequestBody PedidoDTO pedidoDTO ) throws Exception{
        return new ResponseEntity<PedidoDTO>(pedidoService.actualizar(pedidoDTO),
                HttpStatus.OK);
    }

}
