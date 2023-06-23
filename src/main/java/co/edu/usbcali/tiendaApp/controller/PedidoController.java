package co.edu.usbcali.tiendaApp.controller;

import co.edu.usbcali.tiendaApp.dto.PedidoDTO;
import co.edu.usbcali.tiendaApp.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/pedido")
    List<PedidoDTO> buscarTodos() {
        return pedidoService.obtenerTodos();
    }


    @GetMapping("/pedido/{id}")
    ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<PedidoDTO>(pedidoService.buscarPorId(id),
                HttpStatus.OK);
    }

    @PostMapping("/pedido")
    PedidoDTO nuevaPedido(@RequestBody PedidoDTO pedidoDTO) throws Exception {
        return pedidoService.guardar(pedidoDTO);
    }

}
