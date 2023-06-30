package co.edu.usbcali.tiendaApp.controller;


import co.edu.usbcali.tiendaApp.dto.EstadoPedidoDTO;
import co.edu.usbcali.tiendaApp.mapper.EstadoPedidoMapper;
import co.edu.usbcali.tiendaApp.repository.EstadoPedidoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/estadoPedido")
public class EstadoPedidoController {
    private final EstadoPedidoRepository estadoPedidoRepository;

    public EstadoPedidoController(EstadoPedidoRepository estadoPedidoRepository) {
        this.estadoPedidoRepository = estadoPedidoRepository;
    }

    @GetMapping(value = "/buscarTodos")
    List<EstadoPedidoDTO> buscarTodos() {
        return EstadoPedidoMapper.domainToDtoList(estadoPedidoRepository.findAll());
    }

}
