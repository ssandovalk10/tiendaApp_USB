package co.edu.usbcali.tiendaApp.mapper;

import co.edu.usbcali.tiendaApp.domain.Producto;
import co.edu.usbcali.tiendaApp.dto.ProductoDTO;
import co.edu.usbcali.tiendaApp.request.CrearProductoRequest;
import co.edu.usbcali.tiendaApp.response.CrearProductoResponse;

import java.util.List;

public class ProductoMapper {
    public static ProductoDTO domainToDto(Producto producto) {
        return ProductoDTO.builder()
                .id(producto.getId())
                .referencia(producto.getReferencia())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precioUnitario(producto.getPrecioUnitario())
                .unidadesDisponibles(producto.getUnidadesDisponibles())
                .categoriaId((producto.getCategoria() == null) ?
                        null : producto.getCategoria().getId())
                .build();
    }

    public static Producto dtoToDomain(ProductoDTO productoDTO) {
        return Producto.builder()
                .id(productoDTO.getId())
                .referencia(productoDTO.getReferencia())
                .nombre(productoDTO.getNombre())
                .descripcion(productoDTO.getDescripcion())
                .precioUnitario(productoDTO.getPrecioUnitario())
                .unidadesDisponibles(productoDTO.getUnidadesDisponibles())
                .build();
    }

    public static List<ProductoDTO> domainToDtoList(List<Producto> productos) {
        return productos.stream().map(ProductoMapper::domainToDto).toList();
    }

    public static List<Producto> dtoToDomainList(List<ProductoDTO> productosDtos) {
        return productosDtos.stream().map(ProductoMapper::dtoToDomain).toList();
    }

    public static Producto crearRequestToDomain(CrearProductoRequest crearProductoRequest) {
        return Producto.builder()
                .referencia(crearProductoRequest.getReferencia())
                .nombre(crearProductoRequest.getNombre())
                .descripcion(crearProductoRequest.getDescripcion())
                .precioUnitario(crearProductoRequest.getPrecioUnitario())
                .unidadesDisponibles(crearProductoRequest.getUnidadesDisponibles())
                .build();
    }

    public static CrearProductoResponse crearDomainToResponse(Producto producto) {
        return CrearProductoResponse.builder()
                .id(producto.getId())
                .referencia(producto.getReferencia())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precioUnitario(producto.getPrecioUnitario())
                .unidadesDisponibles(producto.getUnidadesDisponibles())
                .nombreCategoria((producto.getCategoria() == null) ?
                        null : producto.getCategoria().getNombre())
                .build();
    }
}
