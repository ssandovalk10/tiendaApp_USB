package co.edu.usbcali.tiendaApp.service.impl;

import co.edu.usbcali.tiendaApp.domain.Categoria;
import co.edu.usbcali.tiendaApp.dto.CategoriaDTO;
import co.edu.usbcali.tiendaApp.exceptions.CategoriaException;
import co.edu.usbcali.tiendaApp.mapper.CategoriaMapper;
import co.edu.usbcali.tiendaApp.repository.CategoriaRepository;
import co.edu.usbcali.tiendaApp.request.CrearCategoriaRequest;
import co.edu.usbcali.tiendaApp.response.CrearCategoriaResponse;
import co.edu.usbcali.tiendaApp.service.CategoriaService;
import co.edu.usbcali.tiendaApp.util.ValidationsUtility;
import co.edu.usbcali.tiendaApp.util.messages.CategoriaServiceMessages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaDTO> obtenerTodos() {
        return CategoriaMapper.domainToDtoList(categoriaRepository.findAll());
    }

    @Override
    public CategoriaDTO buscarPorId(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, CategoriaServiceMessages.ID_VALIDO_MSG);
        return categoriaRepository.findById(id).map(CategoriaMapper::domainToDto).orElseThrow(
                () -> new CategoriaException
                        (String.format(
                                CategoriaServiceMessages.CATEGORIA_NO_ENCONTRADA_POR_ID, id))

        );
    }

    @Override
    public Categoria buscarCategoriaPorId(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, CategoriaServiceMessages.ID_VALIDO_MSG);
        return categoriaRepository.findById(id).orElseThrow(
                () -> new CategoriaException(
                        String.format(CategoriaServiceMessages.CATEGORIA_NO_ENCONTRADA_POR_ID, id)));
    }


    @Override
    public CategoriaDTO guardar(CategoriaDTO categoriaDTO) throws Exception {
        validarCategoria(categoriaDTO, true);

        boolean existePorNombre = categoriaRepository.existsByNombreIgnoreCase(categoriaDTO.getNombre());
        if(existePorNombre) throw new Exception(
                String.format(CategoriaServiceMessages.EXISTE_POR_NOMBRE, categoriaDTO.getNombre()));

        Categoria categoria = CategoriaMapper.dtoToDomain(categoriaDTO);

        return CategoriaMapper.domainToDto(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaDTO actualizar(CategoriaDTO categoriaDTO) throws Exception {
        validarCategoria(categoriaDTO, false);

        boolean existePorNombreYId = categoriaRepository.existsByNombreIgnoreCaseAndIdNot(categoriaDTO.getNombre(), categoriaDTO.getId());

        if(existePorNombreYId) throw new Exception(
                String.format(CategoriaServiceMessages.EXISTE_POR_NOMBRE, categoriaDTO.getNombre()));

        Categoria categoria = buscarCategoriaPorId(categoriaDTO.getId());

        //Modificar Atributos
            categoria.setNombre(categoriaDTO.getNombre());
            categoria.setDescripcion(categoriaDTO.getDescripcion());

            return CategoriaMapper.domainToDto(categoriaRepository.save(categoria));
    }

    @Override
    public String eliminarById(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, CategoriaServiceMessages.ID_VALIDO_MSG);

        Categoria categoria = buscarCategoriaPorId(id);

        categoriaRepository.delete(categoria);

        return String.format(CategoriaServiceMessages.CATEGORIA_ELIMINADA);
    }

    @Override
    public List<CategoriaDTO> buscarPorNombreLike(String nombre) throws Exception {
        ValidationsUtility.stringIsNullOrBlank(nombre, CategoriaServiceMessages.NOMBRE_REQUERIDO);
        return CategoriaMapper.domainToDtoList(categoriaRepository.findByNombreLikeIgnoreCase("%"+nombre+"%"));
    }

    @Override
    public CrearCategoriaResponse crearCategoria(CrearCategoriaRequest crearCategoriaRequest) throws Exception {


        boolean existePorNombre = categoriaRepository.existsByNombreIgnoreCase(crearCategoriaRequest.getNombre());

        if (existePorNombre) throw new Exception(String.format(CategoriaServiceMessages.EXISTE_POR_NOMBRE,crearCategoriaRequest.getNombre()));

        Categoria categoria = CategoriaMapper.crearRequestToDomain(crearCategoriaRequest);

        return CategoriaMapper.crearDomainToResponse(categoriaRepository.save(categoria));
    }

    private void validarCategoria(CategoriaDTO categoriaDTO, boolean esGuardado) throws Exception {
        if(!esGuardado) {
            ValidationsUtility.isNull(categoriaDTO.getId(), CategoriaServiceMessages.ID_REQUERIDO);
        }
        ValidationsUtility.isNull(categoriaDTO, CategoriaServiceMessages.CATEGORIA_NULA);
        ValidationsUtility.stringIsNullOrBlank(categoriaDTO.getNombre(), CategoriaServiceMessages.NOMBRE_REQUERIDO);
        ValidationsUtility.stringIsNullOrBlank(categoriaDTO.getDescripcion(), CategoriaServiceMessages.DESCRIPCION_REQUERIDA);
    }
}
