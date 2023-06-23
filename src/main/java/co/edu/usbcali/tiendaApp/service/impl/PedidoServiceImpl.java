package co.edu.usbcali.tiendaApp.service.impl;

import co.edu.usbcali.tiendaApp.domain.Pedido;
import co.edu.usbcali.tiendaApp.dto.PedidoDTO;
import co.edu.usbcali.tiendaApp.exceptions.PedidoException;
import co.edu.usbcali.tiendaApp.mapper.PedidoMapper;
import co.edu.usbcali.tiendaApp.repository.PedidoRepository;
import co.edu.usbcali.tiendaApp.service.PedidoService;
import co.edu.usbcali.tiendaApp.util.ValidationsUtility;
import co.edu.usbcali.tiendaApp.util.messages.PedidoServiceMessages;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<PedidoDTO> obtenerTodos() {
        return PedidoMapper.domainToDtoList(pedidoRepository.findAll());
    }

    @Override
    public PedidoDTO buscarPorId(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, PedidoServiceMessages.ID_VALIDO_MSG);

        return pedidoRepository.findById(id).map(PedidoMapper::domainToDto).orElseThrow(
                ()-> new PedidoException(String.format(PedidoServiceMessages.PEDIDO_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public PedidoDTO guardar(PedidoDTO pedidoDTO) throws Exception {
        validarPedido(pedidoDTO, true);
        Pedido pedido = PedidoMapper.dtoToDomain(pedidoDTO);
        return PedidoMapper.domainToDto(pedidoRepository.save(pedido));
    }

    @Override
    public PedidoDTO actualizar(PedidoDTO pedidoDTO) throws Exception {
        validarPedido(pedidoDTO, false);
        Pedido pedido = PedidoMapper.dtoToDomain(pedidoDTO);
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
