package co.edu.usbcali.tiendaApp.service.impl;

import co.edu.usbcali.tiendaApp.domain.*;
import co.edu.usbcali.tiendaApp.dto.DetallePedidoDTO;
import co.edu.usbcali.tiendaApp.exceptions.PedidoException;
import co.edu.usbcali.tiendaApp.mapper.DetallePedidoMapper;
import co.edu.usbcali.tiendaApp.repository.DetallePedidoRepository;
import co.edu.usbcali.tiendaApp.service.DetallePedidoService;
import co.edu.usbcali.tiendaApp.service.PedidoService;
import co.edu.usbcali.tiendaApp.service.ProductoService;
import co.edu.usbcali.tiendaApp.util.ValidationsUtility;
import co.edu.usbcali.tiendaApp.util.messages.DetallePedidoServiceMessages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoService pedidoService;
    private final ProductoService productoService;

    public DetallePedidoServiceImpl(DetallePedidoRepository detallePedidoRepository, PedidoService pedidoService, ProductoService productoService) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }

    @Override
    public List<DetallePedido> buscarPorIdPedido(Integer id) throws Exception {
        Pedido pedido = pedidoService.buscarPorId(id);
        return  detallePedidoRepository.findByPedidoId(pedido.getId());
    }

    @Override
    public DetallePedido buscarPorId(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, DetallePedidoServiceMessages.ID_VALIDO_MSG);

        return detallePedidoRepository.findById(id).orElseThrow(
                ()-> new PedidoException(String.format(DetallePedidoServiceMessages.DETALLE_PEDIDO_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public DetallePedidoDTO guardar(DetallePedidoDTO detallePedidoDTO) throws Exception {
        validarDetallePedido(detallePedidoDTO, true);

        Pedido pedido = pedidoService.buscarPorId(detallePedidoDTO.getPedidoId());
        Producto producto = productoService.buscarPorId(detallePedidoDTO.getProductoId());

        boolean existePorProductoYPedido = detallePedidoRepository.existsByPedidoIdAndProductoId(
                detallePedidoDTO.getPedidoId(), detallePedidoDTO.getProductoId());

        if(existePorProductoYPedido) throw new Exception(
                String.format(DetallePedidoServiceMessages.EXISTE_POR_PEDIDO_Y_PRODUCTO,
                        pedido.getId(), producto.getNombre())
        );

        DetallePedido detallePedido = DetallePedidoMapper.dtoToDomain(detallePedidoDTO);

        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);


        return DetallePedidoMapper.domainToDto(detallePedidoRepository.save(detallePedido));
    }

    @Override
    public DetallePedidoDTO actualizar(DetallePedidoDTO detallePedidoDTO) throws Exception {
        validarDetallePedido(detallePedidoDTO, false);

        Pedido pedido = pedidoService.buscarPorId(detallePedidoDTO.getPedidoId());
        Producto producto = productoService.buscarPorId(detallePedidoDTO.getProductoId());

       /* boolean existePorPedidoYProducto = detallePedidoRepository.existsByPedidoIdAndProductoId(
                detallePedidoDTO.getPedidoId(), detallePedidoDTO.getProductoId());

        if(!existePorPedidoYProducto) throw new Exception(
                String.format(DetallePedidoServiceMessages.EXISTE_POR_PEDIDO_Y_PRODUCTO,
                        pedido.getId(), producto.getNombre())
        );
*/

        DetallePedido detallePedido = DetallePedidoMapper.dtoToDomain(detallePedidoDTO);
        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);
        return DetallePedidoMapper.domainToDto(detallePedidoRepository.save(detallePedido));
    }



    private void validarDetallePedido(DetallePedidoDTO detallePedidoDTO, boolean esGuardado) throws Exception {
        if(!esGuardado) {
            ValidationsUtility.isNull(detallePedidoDTO.getId(), DetallePedidoServiceMessages.ID_REQUERIDO);
        }
        ValidationsUtility.isNull(detallePedidoDTO, DetallePedidoServiceMessages.DETALLE_PEDIDO_NULO);
        ValidationsUtility.isNull(detallePedidoDTO.getCantidad(), DetallePedidoServiceMessages.CANTIDAD_REQUERIDA);
        ValidationsUtility.isNull(detallePedidoDTO.getValor(), DetallePedidoServiceMessages.VALOR_REQUERIDO);
    }
}
