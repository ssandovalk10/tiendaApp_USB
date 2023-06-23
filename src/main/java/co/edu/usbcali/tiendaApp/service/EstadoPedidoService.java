package co.edu.usbcali.tiendaApp.service;

import co.edu.usbcali.tiendaApp.domain.TipoDocumento;
import co.edu.usbcali.tiendaApp.dto.EstadoPedidoDTO;
import co.edu.usbcali.tiendaApp.dto.TipoDocumentoDTO;

import java.util.List;

public interface EstadoPedidoService {
    List<EstadoPedidoDTO> obtenerTodos();
    EstadoPedidoDTO buscarPorId(Integer id) throws Exception;

}
