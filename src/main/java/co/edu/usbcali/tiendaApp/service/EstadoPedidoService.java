package co.edu.usbcali.tiendaApp.service;

import co.edu.usbcali.tiendaApp.domain.EstadoPedido;
import co.edu.usbcali.tiendaApp.domain.TipoDocumento;
import co.edu.usbcali.tiendaApp.dto.EstadoPedidoDTO;
import co.edu.usbcali.tiendaApp.dto.TipoDocumentoDTO;

import java.util.List;

public interface EstadoPedidoService {
    EstadoPedido buscarEstadoPedidoPorDescripcion(String descripcion) throws Exception;

    List<EstadoPedidoDTO> obtenerTodos();

    EstadoPedido buscarPorId(Integer id) throws Exception;


    EstadoPedidoDTO guardar(EstadoPedidoDTO estadoPedidoDTO) throws Exception;

    EstadoPedidoDTO actualizar(EstadoPedidoDTO estadoPedidoDTO) throws Exception;

}
