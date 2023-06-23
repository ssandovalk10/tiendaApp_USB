package co.edu.usbcali.tiendaApp.mapper;

import co.edu.usbcali.tiendaApp.domain.Cliente;
import co.edu.usbcali.tiendaApp.dto.ClienteDTO;
import co.edu.usbcali.tiendaApp.request.ActualizarClienteRequest;
import co.edu.usbcali.tiendaApp.request.CrearClienteRequest;
import co.edu.usbcali.tiendaApp.response.ActualizarClienteResponse;
import co.edu.usbcali.tiendaApp.response.CrearClienteResponse;
import co.edu.usbcali.tiendaApp.response.ListarClientesResponse;

import java.util.List;

public class ClienteMapper {

    public static ClienteDTO domainToDto(Cliente cliente){
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nombres(cliente.getNombres())
                .apellidos(cliente.getApellidos())
                .documento(cliente.getDocumento())
                .estado(cliente.getEstado())
                .TipoDocumentoId((cliente.getTipoDocumento() == null ) ? null : cliente.getTipoDocumento().getId() )
                .build();
    }

    public static Cliente dtoToDomain(ClienteDTO clienteDTO){
        return Cliente.builder()
                .id(clienteDTO.getId())
                .nombres(clienteDTO.getNombres())
                .apellidos(clienteDTO.getApellidos())
                .documento(clienteDTO.getDocumento())
                .estado(clienteDTO.getEstado())
                .build();
    }

    public static List<ClienteDTO> domainToDtoList(List<Cliente> cliente){
        return cliente.stream().map(ClienteMapper::domainToDto).toList();
    }
    public static List<Cliente> dtoToDomainList(List<ClienteDTO> clientesDtos){
        return clientesDtos.stream().map(ClienteMapper::dtoToDomain).toList();
    }

    public static ListarClientesResponse domainToResponse(Cliente cliente){
        return ListarClientesResponse.builder()
                .id(cliente.getId())
                .nombres(cliente.getNombres())
                .apellidos(cliente.getApellidos())
                .documento(cliente.getDocumento())
                .estado(cliente.getEstado())
                .build();
    }


    public static List<ListarClientesResponse> domainToResponseList(List<Cliente> cliente){
        return cliente.stream().map(ClienteMapper::domainToResponse).toList();
    }

    public static Cliente crearRequestToDomain(CrearClienteRequest crearClienteRequest) {
        return Cliente.builder()
                .nombres(crearClienteRequest.getNombres())
                .apellidos(crearClienteRequest.getApellidos())
                .documento(crearClienteRequest.getDocumento())
                .estado(crearClienteRequest.getEstado())
                .build();
    }


    public static CrearClienteResponse crearDomainToResponse(Cliente cliente) {
        return CrearClienteResponse.builder()
                .id(cliente.getId())
                .nombres(cliente.getNombres())
                .apellidos(cliente.getApellidos())
                .documento(cliente.getDocumento())
                .estado(cliente.getEstado())
                .tipoDocumentoDescripcion((cliente.getTipoDocumento() == null) ? null :
                        cliente.getTipoDocumento().getDescripcion())
                .build();
    }

    public static Cliente actualizarRequestToDomain(ActualizarClienteRequest actualizarClienteRequest) {
        return Cliente.builder()
                .id(actualizarClienteRequest.getId())
                .nombres(actualizarClienteRequest.getNombres())
                .apellidos(actualizarClienteRequest.getApellidos())
                .documento(actualizarClienteRequest.getDocumento())
                .estado(actualizarClienteRequest.getEstado())
                .build();
    }

    public static ActualizarClienteResponse actualizarClienteResponse(Cliente cliente) {
        return ActualizarClienteResponse.builder()
                .nombres(cliente.getNombres())
                .apellidos(cliente.getApellidos())
                .documento(cliente.getDocumento())
                .estado(cliente.getEstado())
                .tipoDocumentoDescripcion((cliente.getTipoDocumento() == null) ? null :
                        cliente.getTipoDocumento().getDescripcion())
                .build();
    }
}
