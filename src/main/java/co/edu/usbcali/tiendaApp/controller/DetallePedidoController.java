package co.edu.usbcali.tiendaApp.controller;


import co.edu.usbcali.tiendaApp.domain.DetallePedido;
import co.edu.usbcali.tiendaApp.dto.DetallePedidoDTO;

import co.edu.usbcali.tiendaApp.service.DetallePedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/detallePedido")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }


    @GetMapping(value = "/buscarPorPedidoId/")
    List<DetallePedido> buscarPorPedidoId(@RequestParam("id") Integer id) throws Exception {
        return detallePedidoService.buscarPorIdPedido(id);
    }

    @GetMapping(value = "/buscarPorId/")
    DetallePedido buscarPorId(@RequestParam("id") Integer id) throws Exception {
        return detallePedidoService.buscarPorId(id);
    }


    @PostMapping("/nuevaDetallePedido")
    DetallePedidoDTO nuevaDetallePedido(@RequestBody @Valid DetallePedidoDTO detallePedidoDTO) throws Exception {
        return detallePedidoService.guardar(detallePedidoDTO);
    }

    @PutMapping("/actualizarDetallePedido")
    ResponseEntity<DetallePedidoDTO> actualizarDetallePedido(@RequestBody DetallePedidoDTO detallePedidoDTO ) throws Exception{
        return new ResponseEntity<DetallePedidoDTO>(detallePedidoService.actualizar(detallePedidoDTO),
                HttpStatus.OK);
    }
}
