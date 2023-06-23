package co.edu.usbcali.tiendaApp.service.impl;

import co.edu.usbcali.tiendaApp.domain.TipoDocumento;
import co.edu.usbcali.tiendaApp.dto.TipoDocumentoDTO;
import co.edu.usbcali.tiendaApp.exceptions.TipoDocumentoException;
import co.edu.usbcali.tiendaApp.mapper.TipoDocumentoMapper;
import co.edu.usbcali.tiendaApp.repository.TipoDocumentoRepository;
import co.edu.usbcali.tiendaApp.service.TipoDocumentoService;
import co.edu.usbcali.tiendaApp.util.ValidationsUtility;
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
        ValidationsUtility.integerIsNullOrLessZero(id, TipoDocumentoServiceMessages.ID_VALIDO_MESSAGE);
        return tipoDocumentoRepository.findById(id).orElseThrow(
                () -> new TipoDocumentoException(
                        String.format(TipoDocumentoServiceMessages.TIPO_DOCUMENTO_NO_ENCONTRADO_POR_ID, id)));
    }
}
