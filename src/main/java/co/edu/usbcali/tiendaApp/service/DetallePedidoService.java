package co.edu.usbcali.tiendaApp.service;

import co.edu.usbcali.tiendaApp.dto.DetallePedidoDTO;


import java.util.List;

public interface DetallePedidoService {


    DetallePedidoDTO buscarPorIdPedido(Integer id) throws Exception;
    DetallePedidoDTO buscarIdDetallePedido(Integer id) throws Exception;
}
