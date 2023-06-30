package co.edu.usbcali.tiendaApp.service.impl;

import co.edu.usbcali.tiendaApp.domain.Categoria;
import co.edu.usbcali.tiendaApp.domain.TipoDocumento;
import co.edu.usbcali.tiendaApp.dto.CategoriaDTO;
import co.edu.usbcali.tiendaApp.dto.TipoDocumentoDTO;
import co.edu.usbcali.tiendaApp.exceptions.TipoDocumentoException;
import co.edu.usbcali.tiendaApp.mapper.CategoriaMapper;
import co.edu.usbcali.tiendaApp.mapper.TipoDocumentoMapper;
import co.edu.usbcali.tiendaApp.repository.TipoDocumentoRepository;
import co.edu.usbcali.tiendaApp.service.TipoDocumentoService;
import co.edu.usbcali.tiendaApp.util.ValidationsUtility;
import co.edu.usbcali.tiendaApp.util.messages.CategoriaServiceMessages;
import co.edu.usbcali.tiendaApp.util.messages.TipoDocumentoServiceMessages;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private final TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoServiceImpl(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Override
    public List<TipoDocumentoDTO> obtenerTodos() {
      return TipoDocumentoMapper.domainToDtoList(tipoDocumentoRepository.findAll());
    }

    @Override
    public TipoDocumentoDTO buscarPorId(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, TipoDocumentoServiceMessages.ID_VALIDO_MSG);

        return tipoDocumentoRepository.findById(id).map(TipoDocumentoMapper::domainToDto).orElseThrow(
                ()-> new TipoDocumentoException(String.format(TipoDocumentoServiceMessages.TIPO_DOCUMENTO_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public TipoDocumento buscarTipoDocumentoPorId(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, TipoDocumentoServiceMessages.ID_VALIDO_MSG);
        return tipoDocumentoRepository.findById(id).orElseThrow(
                () -> new TipoDocumentoException(
                        String.format(TipoDocumentoServiceMessages.TIPO_DOCUMENTO_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public TipoDocumentoDTO guardar(TipoDocumentoDTO tipoDocumentoDTO) throws Exception {
        validarTipoDocumento(tipoDocumentoDTO, true);

        boolean existePorDescripcion = tipoDocumentoRepository.existsByDescripcionIgnoreCase(tipoDocumentoDTO.getDescripcion());
        if(existePorDescripcion) throw new Exception(
                String.format(CategoriaServiceMessages.EXISTE_POR_NOMBRE, tipoDocumentoDTO.getDescripcion()));

        TipoDocumento tipoDocumento = TipoDocumentoMapper.dtoToDomain(tipoDocumentoDTO);

        return TipoDocumentoMapper.domainToDto(tipoDocumentoRepository.save(tipoDocumento));
    }

    @Override
    public TipoDocumentoDTO actualizar(TipoDocumentoDTO tipoDocumentoDTO) throws Exception {
        validarTipoDocumento(tipoDocumentoDTO, false);

        boolean existePorDescripcionYOtroId = tipoDocumentoRepository.existsByDescripcionIgnoreCaseAndIdNot(tipoDocumentoDTO.getDescripcion(), tipoDocumentoDTO.getId());
        if(existePorDescripcionYOtroId) throw new Exception(
                String.format(TipoDocumentoServiceMessages.EXISTE_POR_DESCRIPCION, tipoDocumentoDTO.getDescripcion()));

        TipoDocumento tipoDocumento = buscarTipoDocumentoPorId(tipoDocumentoDTO.getId());

        //Modificar atributos
        tipoDocumento.setDescripcion(tipoDocumentoDTO.getDescripcion());

        return TipoDocumentoMapper.domainToDto(tipoDocumentoRepository.save(tipoDocumento));
    }


    private void validarTipoDocumento(TipoDocumentoDTO tipoDocumentoDTO, boolean esGuardado) throws Exception {
        if(!esGuardado) {
            ValidationsUtility.isNull(tipoDocumentoDTO.getId(), TipoDocumentoServiceMessages.ID_VALIDO_MSG);
        }
        ValidationsUtility.isNull(tipoDocumentoDTO, TipoDocumentoServiceMessages.TIPODOCUMENTO_NULO);
        ValidationsUtility.stringIsNullOrBlank(tipoDocumentoDTO.getDescripcion(), TipoDocumentoServiceMessages.TIPODOCUMENTO_DESCRIPCION_REQUERIDA);
    }
}
