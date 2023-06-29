package co.edu.usbcali.tiendaApp.mapper;

import co.edu.usbcali.tiendaApp.domain.Categoria;
import co.edu.usbcali.tiendaApp.dto.CategoriaDTO;
import co.edu.usbcali.tiendaApp.request.CrearCategoriaRequest;
import co.edu.usbcali.tiendaApp.response.CrearCategoriaResponse;

import java.util.List;

public class CategoriaMapper {

    public static CategoriaDTO domainToDto(Categoria categoria){
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }

    public static Categoria dtoToDomain(CategoriaDTO categoriaDTO){
        return Categoria.builder()
                .id(categoriaDTO.getId())
                .nombre(categoriaDTO.getNombre())
                .descripcion(categoriaDTO.getDescripcion())
                .build();
    }

    public static List<CategoriaDTO> domainToDtoList(List<Categoria> categorias){
        return categorias.stream().map(CategoriaMapper::domainToDto).toList();
    }
    public static List<Categoria> dtoToDomainList(List<CategoriaDTO> categoriasDtos){
        return categoriasDtos.stream().map(CategoriaMapper::dtoToDomain).toList();
    }

    public static Categoria crearRequestToDomain(CrearCategoriaRequest crearCategoriaRequest) {
        return Categoria.builder()
                .nombre(crearCategoriaRequest.getNombre())
                .descripcion(crearCategoriaRequest.getDescripcion())
                .build();
    }

    public static CrearCategoriaResponse crearDomainToResponse(Categoria categoria) {
        return CrearCategoriaResponse.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }


}
