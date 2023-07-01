package co.edu.usbcali.tiendaApp.controller;

import co.edu.usbcali.tiendaApp.dto.PedidoDTO;
import co.edu.usbcali.tiendaApp.dto.ProductoDTO;
import co.edu.usbcali.tiendaApp.service.PedidoService;
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


    @GetMapping("/buscarPorId/")
    ResponseEntity<PedidoDTO> buscarPorId(@RequestParam Integer id) throws Exception {
        return new ResponseEntity<PedidoDTO>(pedidoService.buscarPorId(id),
                HttpStatus.OK);
    }

    @PostMapping("/nuevaPedido")
    PedidoDTO nuevaPedido(@RequestBody PedidoDTO pedidoDTO) throws Exception {
        return pedidoService.guardar(pedidoDTO);
    }

    @PutMapping("/actualizarPedido")
    ResponseEntity<PedidoDTO> actualizarPedido(@RequestBody PedidoDTO pedidoDTO ) throws Exception{
        return new ResponseEntity<PedidoDTO>(pedidoService.actualizar(pedidoDTO),
                HttpStatus.OK);
    }

}
