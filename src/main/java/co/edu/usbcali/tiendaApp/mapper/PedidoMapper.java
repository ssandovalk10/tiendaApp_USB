package co.edu.usbcali.tiendaApp.mapper;

import co.edu.usbcali.tiendaApp.domain.Pedido;
import co.edu.usbcali.tiendaApp.dto.PedidoDTO;

import java.util.List;

public class PedidoMapper {

    public static PedidoDTO domainToDto(Pedido pedido){
        return PedidoDTO.builder()
                .id(pedido.getId())
                .fecha(pedido.getFecha())
                .total(pedido.getTotal())
                .clienteId((pedido.getCliente() == null) ? null : pedido.getCliente().getId())
                .estadoPedidoId((pedido.getEstadoPedido() == null) ? null : pedido.getEstadoPedido().getId())
                .build();
    }

    public static Pedido dtoToDomain(PedidoDTO pedidoDTO){
        return Pedido.builder()
                .id(pedidoDTO.getId())
                .fecha(pedidoDTO.getFecha())
                .total(pedidoDTO.getTotal())
                .build();
    }

    public static List<PedidoDTO> domainToDtoList(List<Pedido> pedidos){
        return pedidos.stream().map(PedidoMapper::domainToDto).toList();
    }

    public static List<Pedido> dtoToDomainList(List<PedidoDTO> pedidosDtos){
        return pedidosDtos.stream().map(PedidoMapper::dtoToDomain).toList();
    }
}
