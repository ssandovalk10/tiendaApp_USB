package co.edu.usbcali.tiendaApp.service;

import co.edu.usbcali.tiendaApp.domain.Producto;
import co.edu.usbcali.tiendaApp.dto.ProductoDTO;
import co.edu.usbcali.tiendaApp.request.CrearProductoRequest;
import co.edu.usbcali.tiendaApp.response.CrearProductoResponse;

import java.util.List;

public interface ProductoService {
    CrearProductoResponse guardarNuevo(CrearProductoRequest crearProductoRequest) throws Exception;
    List<ProductoDTO> obtenerTodos();

    List<Producto> listarTodos();

    ProductoDTO actualizar(ProductoDTO productoDto) throws Exception;

    Producto buscarPorId(Integer id) throws Exception;

    List<ProductoDTO> buscarPorNombreLike(String nombre) throws Exception;
}
