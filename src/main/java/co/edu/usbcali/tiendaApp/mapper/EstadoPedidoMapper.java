package co.edu.usbcali.tiendaApp.mapper;

import co.edu.usbcali.tiendaApp.domain.EstadoPedido;
import co.edu.usbcali.tiendaApp.dto.EstadoPedidoDTO;

import java.util.List;

public class EstadoPedidoMapper {

    public static EstadoPedidoDTO domainToDto(EstadoPedido estadoPedido) {
        return EstadoPedidoDTO.builder()
                .id(estadoPedido.getId())
                .descripcion(estadoPedido.getDescripcion())
                .build();
    }

    public static EstadoPedido dtoToDomain(EstadoPedidoDTO estadoPedidoDTO) {
        return EstadoPedido.builder()
                .id(estadoPedidoDTO.getId())
                .descripcion(estadoPedidoDTO.getDescripcion())
                .build();
    }

    public static List<EstadoPedidoDTO> domainToDtoList(List<EstadoPedido> estadosPedido) {
        return estadosPedido.stream().map(EstadoPedidoMapper::domainToDto).toList();
    }

    public static List<EstadoPedido> dtoToDomainList(List<EstadoPedidoDTO> estadosPedidoDtos) {
        return estadosPedidoDtos.stream().map(EstadoPedidoMapper::dtoToDomain).toList();
    }
}
