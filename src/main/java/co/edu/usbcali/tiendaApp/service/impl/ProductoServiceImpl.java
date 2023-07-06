package co.edu.usbcali.tiendaApp.service.impl;

import co.edu.usbcali.tiendaApp.domain.Categoria;
import co.edu.usbcali.tiendaApp.domain.Producto;
import co.edu.usbcali.tiendaApp.dto.ProductoDTO;
import co.edu.usbcali.tiendaApp.exceptions.ProductoException;
import co.edu.usbcali.tiendaApp.mapper.CategoriaMapper;
import co.edu.usbcali.tiendaApp.mapper.ProductoMapper;
import co.edu.usbcali.tiendaApp.repository.ProductoRepository;
import co.edu.usbcali.tiendaApp.request.CrearProductoRequest;
import co.edu.usbcali.tiendaApp.response.CrearProductoResponse;
import co.edu.usbcali.tiendaApp.service.CategoriaService;
import co.edu.usbcali.tiendaApp.service.ProductoService;
import co.edu.usbcali.tiendaApp.util.ValidationsUtility;
import co.edu.usbcali.tiendaApp.util.messages.CategoriaServiceMessages;
import co.edu.usbcali.tiendaApp.util.messages.ProductoServiceMessages;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    private final CategoriaService categoriaService;

    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaService categoriaService) {
        this.productoRepository = productoRepository;
        this.categoriaService = categoriaService;
    }


    @Override
    public CrearProductoResponse guardarNuevo(CrearProductoRequest crearProductoRequest) throws Exception {
        // Ya se hizo validación de campos
        // Buscar la Categoría en Base de Datos
        Categoria categoria = categoriaService.buscarCategoriaPorId(crearProductoRequest.getCategoriaId());

        // Validar si ya existe la referencia en la Categoría
        boolean existeReferenciaEnCategoria = productoRepository
                .existsByCategoriaIdAndReferencia(crearProductoRequest.getCategoriaId(), crearProductoRequest.getReferencia());
        if (existeReferenciaEnCategoria) throw new Exception(
                String.format(ProductoServiceMessages.EXISTE_REFERENCIA_EN_CATEGORIA,
                        crearProductoRequest.getReferencia(), (categoria.getId()+" - "+categoria.getNombre()))
        );

        // Mapear del request al Producto
        Producto producto = ProductoMapper.crearRequestToDomain(crearProductoRequest);

        // Inyectar la categoría buscada al Objeto del dominio Producto
        producto.setCategoria(categoria);

        // 1. Guarda el nuevo producto
        // 2. Mapea el producto al Response
        return ProductoMapper.crearDomainToResponse(productoRepository.save(producto));
    }


    @Override
    public List<ProductoDTO> buscarPorNombreLike(String nombre) throws Exception {
        ValidationsUtility.stringIsNullOrBlank(nombre, ProductoServiceMessages.NOMBRE_REQUERIDO);
        return ProductoMapper.domainToDtoList(productoRepository.findByNombreLikeIgnoreCase("%"+nombre+"%"));
    }

    @Override
    public Producto buscarPorId(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, ProductoServiceMessages.ID_VALIDO_MSG);
        return productoRepository.findById(id).orElseThrow(
                () -> new ProductoException
                        (String.format(
                                ProductoServiceMessages.PRODUCTO_NO_ENCONTRADA_POR_ID, id))

        );
    }

    @Override
    public List<ProductoDTO> obtenerTodos() {
        return ProductoMapper.domainToDtoList(productoRepository.findAll());
    }

    @Override
    public List<Producto> listarTodos() {
       return productoRepository.findAll();
    }

    @Override
    public ProductoDTO actualizar(ProductoDTO productoDto) throws Exception {
        validarProducto(productoDto, false);
        Categoria categoria = categoriaService.buscarCategoriaPorId(productoDto.getCategoriaId());

        boolean existePorNombreYOtroId = productoRepository.existsByNombreIgnoreCaseAndIdNot(productoDto.getNombre(),productoDto.getId());
        if (existePorNombreYOtroId) throw new Exception(String.format(ProductoServiceMessages.EXISTE_POR_NOMBRE,productoDto.getNombre()));

        Producto producto = buscarPorId(productoDto.getId());
        producto.setNombre(productoDto.getNombre());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setReferencia(productoDto.getReferencia());
        producto.setPrecioUnitario(productoDto.getPrecioUnitario());
        producto.setUnidadesDisponibles(productoDto.getUnidadesDisponibles());
        producto.setCategoria(categoria);

        return ProductoMapper.domainToDto(productoRepository.save(producto));
    }

    private void validarProducto(ProductoDTO productoDto, boolean esGuardado) throws Exception {
        if(!esGuardado) {
            ValidationsUtility.isNull(productoDto.getId(), ProductoServiceMessages.ID_REQUERIDO);
        }
        ValidationsUtility.isNull(productoDto, ProductoServiceMessages.PRODUCTO_NULO);
        ValidationsUtility.stringIsNullOrBlank(productoDto.getNombre(), ProductoServiceMessages.NOMBRE_REQUERIDO);
        ValidationsUtility.stringIsNullOrBlank(productoDto.getReferencia(), ProductoServiceMessages.REFERENCIA_REQUERIDA);
        ValidationsUtility.bigDecimalIsNullOrLessZero(productoDto.getPrecioUnitario(), ProductoServiceMessages.PRECIO_UNITARIO_REQUERIDO);
        ValidationsUtility.bigDecimalIsNullOrLessZero(productoDto.getUnidadesDisponibles(), ProductoServiceMessages.UNIDADES_DISPONIBLES_REQUERIDO);
    }

}
