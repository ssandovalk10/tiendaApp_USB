package co.edu.usbcali.tiendaApp.service;

import co.edu.usbcali.tiendaApp.domain.DetallePedido;
import co.edu.usbcali.tiendaApp.domain.Pedido;
import co.edu.usbcali.tiendaApp.dto.DetallePedidoDTO;
import co.edu.usbcali.tiendaApp.dto.PedidoDTO;


import java.util.List;

public interface DetallePedidoService {

    List<DetallePedido> buscarPorIdPedido(Integer id) throws Exception;
    DetallePedido buscarPorId(Integer id) throws Exception;
    DetallePedidoDTO guardar(DetallePedidoDTO detallePedidoDTO) throws Exception;
    DetallePedidoDTO actualizar(DetallePedidoDTO detallePedidoDTO) throws Exception ;


}
