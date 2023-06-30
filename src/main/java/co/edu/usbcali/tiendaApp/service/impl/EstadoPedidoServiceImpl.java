package co.edu.usbcali.tiendaApp.service.impl;

import co.edu.usbcali.tiendaApp.domain.EstadoPedido;
import co.edu.usbcali.tiendaApp.dto.EstadoPedidoDTO;
import co.edu.usbcali.tiendaApp.exceptions.EstadoPedidoException;
import co.edu.usbcali.tiendaApp.mapper.EstadoPedidoMapper;
import co.edu.usbcali.tiendaApp.repository.EstadoPedidoRepository;
import co.edu.usbcali.tiendaApp.service.EstadoPedidoService;
import co.edu.usbcali.tiendaApp.util.ValidationsUtility;
import co.edu.usbcali.tiendaApp.util.messages.EstadoPedidoServiceMessages;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstadoPedidoServiceImpl implements EstadoPedidoService {

    private final EstadoPedidoRepository estadoPedidoRepository;

    public EstadoPedidoServiceImpl(EstadoPedidoRepository estadoPedidoRepository) {
        this.estadoPedidoRepository = estadoPedidoRepository;
    }

    @Override
    public EstadoPedido buscarEstadoPedidoPorDescripcion(String descripcion) throws Exception {
        return estadoPedidoRepository.findByDescripcionIgnoreCase(descripcion).orElseThrow(
                () -> new EstadoPedidoException(String.format(EstadoPedidoServiceMessages.NO_ENCONTRADO_POR_DESCRIPCION, descripcion)));
    }

    @Override
    public List<EstadoPedidoDTO> obtenerTodos() {
        return EstadoPedidoMapper.domainToDtoList(estadoPedidoRepository.findAllByOrderByDescripcionAsc());
    }

    @Override
    public EstadoPedido buscarPorId(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, EstadoPedidoServiceMessages.ID_VALIDO_MSG);
        return estadoPedidoRepository.findById(id).orElseThrow(
                () -> new EstadoPedidoException
                        (String.format(
                                EstadoPedidoServiceMessages.ESTADO_PEDIDO_NO_ENCONTRADO_POR_ID, id))

        );
    }

    @Override
    public EstadoPedidoDTO guardar(EstadoPedidoDTO estadoPedidoDTO) throws Exception {
        buscarEstadoPedidoPorDescripcion(estadoPedidoDTO.getDescripcion());
        EstadoPedido estadoPedido = EstadoPedidoMapper.dtoToDomain(estadoPedidoDTO);
        return EstadoPedidoMapper.domainToDto(estadoPedidoRepository.save(estadoPedido));
    }

    @Override
    public EstadoPedidoDTO actualizar(EstadoPedidoDTO estadoPedidoDTO) throws Exception {
        boolean existePorDescripcionYOtroId = estadoPedidoRepository.existsByDescripcionIgnoreCaseAndIdNot(estadoPedidoDTO.getDescripcion(), estadoPedidoDTO.getId());
        if(existePorDescripcionYOtroId) throw new Exception(
                String.format(EstadoPedidoServiceMessages.EXISTE_POR_NOMBRE, estadoPedidoDTO.getDescripcion()));

        EstadoPedido estadoPedido = buscarPorId(estadoPedidoDTO.getId());

        estadoPedido.setDescripcion(estadoPedidoDTO.getDescripcion());

        return EstadoPedidoMapper.domainToDto(estadoPedidoRepository.save(estadoPedido));
    }


}
