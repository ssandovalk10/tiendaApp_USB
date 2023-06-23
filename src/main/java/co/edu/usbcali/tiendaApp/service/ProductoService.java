package co.edu.usbcali.tiendaApp.service;


import co.edu.usbcali.tiendaApp.request.ActualizarProductoRequest;
import co.edu.usbcali.tiendaApp.request.CrearProductoRequest;
import co.edu.usbcali.tiendaApp.response.*;

import java.util.List;

public interface ProductoService {

    List<ListarProductosResponse> obtenerTodos();
    CrearProductoResponse guardarNuevo(CrearProductoRequest crearProductoRequest) throws Exception;

    ActualizarProductoResponse actualizarProducto(ActualizarProductoRequest actualizarProductoRequest) throws Exception;

}
