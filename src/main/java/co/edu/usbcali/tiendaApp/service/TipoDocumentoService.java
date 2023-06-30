package co.edu.usbcali.tiendaApp.service;

import co.edu.usbcali.tiendaApp.domain.TipoDocumento;
import co.edu.usbcali.tiendaApp.dto.TipoDocumentoDTO;

import java.util.List;

public interface TipoDocumentoService {
    List<TipoDocumentoDTO> obtenerTodos();
    TipoDocumentoDTO buscarPorId(Integer id) throws Exception;
    TipoDocumento buscarTipoDocumentoPorId(Integer id) throws Exception;
    TipoDocumentoDTO guardar(TipoDocumentoDTO tipoDocumentoDTO) throws Exception;
    TipoDocumentoDTO actualizar(TipoDocumentoDTO tipoDocumentoDTO) throws Exception;

}
