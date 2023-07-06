package co.edu.usbcali.tiendaApp.service.impl;

import co.edu.usbcali.tiendaApp.domain.*;
import co.edu.usbcali.tiendaApp.dto.PedidoDTO;
import co.edu.usbcali.tiendaApp.exceptions.PedidoException;
import co.edu.usbcali.tiendaApp.mapper.PedidoMapper;
import co.edu.usbcali.tiendaApp.repository.PedidoRepository;
import co.edu.usbcali.tiendaApp.service.ClienteService;
import co.edu.usbcali.tiendaApp.service.EstadoPedidoService;
import co.edu.usbcali.tiendaApp.service.PedidoService;
import co.edu.usbcali.tiendaApp.util.ValidationsUtility;
import co.edu.usbcali.tiendaApp.util.messages.PedidoServiceMessages;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final EstadoPedidoService estadoPedidoService;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteService clienteService, EstadoPedidoService estadoPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
        this.estadoPedidoService = estadoPedidoService;
    }

    @Override
    public List<PedidoDTO> obtenerTodos() {
        return PedidoMapper.domainToDtoList(pedidoRepository.findAll());
    }

    @Override
    public List<Pedido> buscarPorClienteId(Integer id) {
        return  pedidoRepository.findByClienteId(id);
    }

    @Override
    public List<Pedido> buscarPorEstadoPedidoId(Integer id) {
        return  pedidoRepository.findByEstadoPedidoId(id);
    }

    @Override
    public Pedido buscarPorId(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, PedidoServiceMessages.ID_VALIDO_MSG);

        return pedidoRepository.findById(id).orElseThrow(
                ()-> new PedidoException(String.format(PedidoServiceMessages.PEDIDO_NO_ENCONTRADO_POR_ID, id)));
    }


    @Override
    public PedidoDTO guardar(PedidoDTO pedidoDTO) throws Exception {
        validarPedido(pedidoDTO, true);

        Cliente cliente = clienteService.buscarClientePorId(pedidoDTO.getClienteId());

        EstadoPedido estadoPedido = estadoPedidoService.buscarPorId(pedidoDTO.getEstadoPedidoId());

        Pedido pedido = PedidoMapper.dtoToDomain(pedidoDTO);

        pedido.setCliente(cliente);
        pedido.setEstadoPedido(estadoPedido);


        return PedidoMapper.domainToDto(pedidoRepository.save(pedido));
    }

    @Override
    public PedidoDTO actualizar(PedidoDTO pedidoDTO) throws Exception {
        validarPedido(pedidoDTO, false);
        Cliente cliente = clienteService.buscarClientePorId(pedidoDTO.getClienteId());

        EstadoPedido estadoPedido = estadoPedidoService.buscarPorId(pedidoDTO.getEstadoPedidoId());

        Pedido pedido = PedidoMapper.dtoToDomain(pedidoDTO);
        pedido.setCliente(cliente);
        pedido.setEstadoPedido(estadoPedido);

        return PedidoMapper.domainToDto(pedidoRepository.save(pedido));
    }

    private void validarPedido(PedidoDTO pedidoDTO, boolean esGuardado) throws Exception {
        if(!esGuardado) {
            ValidationsUtility.isNull(pedidoDTO.getId(), PedidoServiceMessages.ID_REQUERIDO);
        }
        ValidationsUtility.isNull(pedidoDTO, PedidoServiceMessages.PEDIDO_NULO);
        ValidationsUtility.isNull(pedidoDTO.getFecha(), PedidoServiceMessages.FECHA_REQUERIDA);
        ValidationsUtility.isNull(pedidoDTO.getTotal(), PedidoServiceMessages.TOTAL_REQUERIDO);
    }
}
