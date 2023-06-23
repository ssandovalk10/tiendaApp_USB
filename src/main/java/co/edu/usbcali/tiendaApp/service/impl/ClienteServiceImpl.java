package co.edu.usbcali.tiendaApp.service.impl;

import co.edu.usbcali.tiendaApp.domain.Cliente;
import co.edu.usbcali.tiendaApp.domain.TipoDocumento;
import co.edu.usbcali.tiendaApp.dto.ClienteDTO;
import co.edu.usbcali.tiendaApp.exceptions.ClienteException;
import co.edu.usbcali.tiendaApp.mapper.ClienteMapper;
import co.edu.usbcali.tiendaApp.repository.ClienteRepository;
import co.edu.usbcali.tiendaApp.repository.TipoDocumentoRepository;
import co.edu.usbcali.tiendaApp.request.CrearClienteRequest;
import co.edu.usbcali.tiendaApp.response.CrearClienteResponse;
import co.edu.usbcali.tiendaApp.service.ClienteService;
import co.edu.usbcali.tiendaApp.util.ValidationsUtility;
import co.edu.usbcali.tiendaApp.util.messages.ClienteServiceMessages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final TipoDocumentoServiceImpl tipoDocumentoService;

    public ClienteServiceImpl(ClienteRepository clienteRepository, TipoDocumentoRepository tipoDocumentoRepository, TipoDocumentoServiceImpl tipoDocumentoService){
        this.clienteRepository = clienteRepository;
        this.tipoDocumentoService = tipoDocumentoService;
    }
    @Override
    public List<ClienteDTO> obtenerTodos() {

        return  ClienteMapper.domainToDtoList(clienteRepository.findAll());
        /*List<Cliente> clientes = ClienteRepository.findAll();
        List<ClienteDTO> clienteDTOS = ClienteMapper.domainToDtoList(clientes);
        return clienteDTOS;*/

    }

    @Override
    public ClienteDTO buscarPorId(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, ClienteServiceMessages.ID_VALIDO_MSG);

        //lANDAS
        return clienteRepository.findById(id).map(ClienteMapper::domainToDto).orElseThrow(
                () -> new ClienteException(String.format(ClienteServiceMessages.CLIENTE_NO_ENCONTRADO_POR_ID, id)));

        /*
        *
        Cliente cliente = clienteRepository.getReferenceById(id);
        if( cliente == null){
            throw new Exception("No se ha encontrado el cliente con Id" + id);
        }

        ClienteDTO clienteDTO = ClienteMapper.domainToDto(cliente);

        return clienteDTO;*/
    }

    @Override
    public ClienteDTO guardar(ClienteDTO clienteDTO) throws Exception {
        validarCliente(clienteDTO);
        Cliente cliente = ClienteMapper.dtoToDomain(clienteDTO);
        TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoPorId(
                clienteDTO.getTipoDocumentoId());
        cliente.setTipoDocumento(tipoDocumento);

        return ClienteMapper.domainToDto(clienteRepository.save(cliente));
    }

    public CrearClienteResponse crearCliente(CrearClienteRequest crearClienteRequest) throws Exception {
        // Mapear cliente hacia el Domain
        Cliente cliente = ClienteMapper.crearRequestToDomain(crearClienteRequest);

        // Buscar el tipo de documento (Domain)
        TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoPorId(
                crearClienteRequest.getTipoDocumentoId());

        // Validar si ya existe un cliente con la llave Documento-TipoDocumento
        boolean existePorTipoYDocumento = clienteRepository.existsByTipoDocumentoIdAndDocumento(
                crearClienteRequest.getTipoDocumentoId(), crearClienteRequest.getDocumento());

        // Si el cliente ya existe por la llave Documento-TipoDocumento entonces lanza excepción
        if(existePorTipoYDocumento) throw new Exception(
                String.format(ClienteServiceMessages.EXISTE_POR_TIPO_DOCUMENTO_Y_DOCUMENTO,
                        tipoDocumento.getDescripcion(), crearClienteRequest.getDocumento())
        );

        // Hidratar el Tipo de Documento del cliente, dado que no fue mapeada
        cliente.setTipoDocumento(tipoDocumento);

        // Guarda el nuevo cliente, retorna el Response a la capa Superior utilizando el Mapper.
        return ClienteMapper.crearDomainToResponse(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDTO actualizar(ClienteDTO clienteDTO) {
        return null;
    }

    private void validarCliente(ClienteDTO clienteDTO) throws Exception {
        ValidationsUtility.isNull(clienteDTO, ClienteServiceMessages.CLIENTE_NULO);
        ValidationsUtility.stringIsNullOrBlank(clienteDTO.getNombres(), ClienteServiceMessages.NOMBRES_REQUERIDOS);
        ValidationsUtility.stringIsNullOrBlank(clienteDTO.getApellidos(), ClienteServiceMessages.APELLIDOS_REQUERIDOS);
        ValidationsUtility.stringIsNullOrBlank(clienteDTO.getDocumento(), ClienteServiceMessages.DOCUMENTO_REQUERIDO);
        ValidationsUtility.stringIsNullOrBlank(clienteDTO.getEstado(), ClienteServiceMessages.ESTADO_REQUERIDO);
        ValidationsUtility.lenghtString(clienteDTO.getEstado(), 1, ClienteServiceMessages.ESTADO_LENGHT);
        ValidationsUtility.integerIsNullOrLessZero(clienteDTO.getTipoDocumentoId(), ClienteServiceMessages.TIPO_DOCUMENTO_ID_REQUERIDO);



    }
}
