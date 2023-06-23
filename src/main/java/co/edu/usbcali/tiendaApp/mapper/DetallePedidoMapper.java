package co.edu.usbcali.tiendaApp.mapper;

import co.edu.usbcali.tiendaApp.domain.DetallePedido;
import co.edu.usbcali.tiendaApp.dto.DetallePedidoDTO;

import java.util.List;

public class DetallePedidoMapper {

    public static DetallePedidoDTO domainToDto(DetallePedido detallePedido){
        return DetallePedidoDTO.builder()
                .id(detallePedido.getId())
                .cantidad(detallePedido.getCantidad())
                .valor(detallePedido.getValor())
                .pedidoId((detallePedido.getPedido() == null) ?
                        null : detallePedido.getPedido().getId())
                .productoId((detallePedido.getProducto() == null ) ?
                        null : detallePedido.getProducto().getId())
                .build();
    }

    public static DetallePedido dtoToDomain(DetallePedidoDTO detallePedidoDTO) {
        return DetallePedido.builder()
                .id(detallePedidoDTO.getId())
                .cantidad(detallePedidoDTO.getCantidad())
                .valor(detallePedidoDTO.getValor())
                .build();
    }

    public static List<DetallePedidoDTO> domainToDtoList(List<DetallePedido> detallesPedido) {
        return detallesPedido.stream().map(DetallePedidoMapper::domainToDto).toList();
    }

    public static List<DetallePedido> dtoToDomainList(List<DetallePedidoDTO> detallesPedidoDtos) {
        return detallesPedidoDtos.stream().map(DetallePedidoMapper::dtoToDomain).toList();
    }
}
