package co.edu.usbcali.tiendaApp.service;

import co.edu.usbcali.tiendaApp.domain.Pedido;
import co.edu.usbcali.tiendaApp.dto.PedidoDTO;

import java.util.List;

public interface PedidoService {

    List<PedidoDTO> obtenerTodos();
    List<Pedido> buscarPorClienteId(Integer id);
    List<Pedido> buscarPorEstadoPedidoId(Integer id);
    Pedido buscarPorId(Integer id) throws Exception;
    PedidoDTO guardar(PedidoDTO pedidoDTO) throws Exception;
    PedidoDTO actualizar(PedidoDTO pedidoDTO) throws Exception ;
}
