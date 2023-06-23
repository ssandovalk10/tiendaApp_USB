package co.edu.usbcali.tiendaApp.service;

import co.edu.usbcali.tiendaApp.dto.PedidoDTO;
import co.edu.usbcali.tiendaApp.dto.ProductoDTO;
import co.edu.usbcali.tiendaApp.request.CrearProductoRequest;
import co.edu.usbcali.tiendaApp.response.CrearProductoResponse;

import java.util.List;

public interface ProductoService {
    CrearProductoResponse guardarNuevo(CrearProductoRequest crearProductoRequest) throws Exception;
}
