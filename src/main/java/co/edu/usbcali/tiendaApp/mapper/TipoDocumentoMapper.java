package co.edu.usbcali.tiendaApp.mapper;

import co.edu.usbcali.tiendaApp.domain.TipoDocumento;
import co.edu.usbcali.tiendaApp.dto.TipoDocumentoDTO;

import java.util.List;

public class TipoDocumentoMapper {

    public static TipoDocumentoDTO domainToDto(TipoDocumento tipodocumento){
        return TipoDocumentoDTO.builder()
                .id(tipodocumento.getId())
                .descripcion(tipodocumento.getDescripcion())
                .build();
    }

    public static TipoDocumento dtoToDomain(TipoDocumentoDTO tipoDocumentoDTO){
        return TipoDocumento.builder()
                .id(tipoDocumentoDTO.getId())
                .descripcion(tipoDocumentoDTO.getDescripcion())
                .build();
    }

    public static List<TipoDocumentoDTO> domainToDtoList(List<TipoDocumento> TipoDocumento){
        return TipoDocumento.stream().map(TipoDocumentoMapper::domainToDto).toList();
    }
    public static List<TipoDocumento> dtoToDomainList(List<TipoDocumentoDTO> tipodocumentosDtos){
        return tipodocumentosDtos.stream().map(TipoDocumentoMapper::dtoToDomain).toList();
    }

}
