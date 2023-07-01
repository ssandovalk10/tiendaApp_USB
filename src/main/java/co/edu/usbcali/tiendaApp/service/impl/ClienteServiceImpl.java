package co.edu.usbcali.tiendaApp.service.impl;

import co.edu.usbcali.tiendaApp.domain.Categoria;
import co.edu.usbcali.tiendaApp.domain.Cliente;
import co.edu.usbcali.tiendaApp.domain.Producto;
import co.edu.usbcali.tiendaApp.domain.TipoDocumento;
import co.edu.usbcali.tiendaApp.dto.ClienteDTO;
import co.edu.usbcali.tiendaApp.exceptions.CategoriaException;
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
    public ClienteDTO guardar(ClienteDTO clienteDTO) throws Exception {
        validarCliente(clienteDTO,true);
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
    public Cliente buscarClientePorId(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, ClienteServiceMessages.ID_VALIDO_MSG);
        return clienteRepository.findById(id).orElseThrow(
                () -> new CategoriaException(
                        String.format(ClienteServiceMessages.CLIENTE_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public ClienteDTO actualizar(ClienteDTO clienteDTO) throws Exception {

        validarCliente(clienteDTO,false);

        TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoPorId(
                clienteDTO.getTipoDocumentoId());

        boolean existePorTipoYDocumento = clienteRepository.existsByTipoDocumentoIdAndDocumento(
                clienteDTO.getTipoDocumentoId(), clienteDTO.getDocumento());

        // Si el cliente ya existe por la llave Documento-TipoDocumento entonces lanza excepción
        if(existePorTipoYDocumento) throw new Exception(
                String.format(ClienteServiceMessages.EXISTE_POR_TIPO_DOCUMENTO_Y_DOCUMENTO,
                        tipoDocumento.getDescripcion(), clienteDTO.getDocumento())
        );

        /*boolean existePorTipoDocumentoAndDocumento = clienteRepository.existsByTipoDocumentoIdAndDocumento(clienteDTO.getTipoDocumentoId(),clienteDTO.getDocumento());
        if (existePorTipoDocumentoAndDocumento) throw new Exception(String.format(ClienteServiceMessages.EXISTE_POR_TIPO_DOCUMENTO_Y_DOCUMENTO,tipoDocumento.getDescripcion(), clienteDTO.getDocumento()));*/

        Cliente cliente = buscarClientePorId(clienteDTO.getId());

        cliente.setNombres(clienteDTO.getNombres());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setEstado(cliente.getEstado());
        cliente.setDocumento(cliente.getDocumento());
        cliente.setTipoDocumento(tipoDocumento);

        return ClienteMapper.domainToDto(clienteRepository.save(cliente));
    }

    @Override
    public List<ClienteDTO> buscarPorNombresLike(String nombres) throws Exception {
        ValidationsUtility.stringIsNullOrBlank(nombres, ClienteServiceMessages.NOMBRES_REQUERIDOS);
        return ClienteMapper.domainToDtoList(clienteRepository.findByNombresLikeIgnoreCase("%"+nombres+"%"));
    }

    private void validarCliente(ClienteDTO clienteDTO, boolean esGuardado) throws Exception {
        if(!esGuardado) {
            ValidationsUtility.isNull(clienteDTO.getId(), ClienteServiceMessages.ID_VALIDO_MSG);
        }
        ValidationsUtility.isNull(clienteDTO, ClienteServiceMessages.CLIENTE_NULO);
        ValidationsUtility.stringIsNullOrBlank(clienteDTO.getNombres(), ClienteServiceMessages.NOMBRES_REQUERIDOS);
        ValidationsUtility.stringIsNullOrBlank(clienteDTO.getApellidos(), ClienteServiceMessages.APELLIDOS_REQUERIDOS);
        ValidationsUtility.stringIsNullOrBlank(clienteDTO.getDocumento(), ClienteServiceMessages.DOCUMENTO_REQUERIDO);
        ValidationsUtility.stringIsNullOrBlank(clienteDTO.getEstado(), ClienteServiceMessages.ESTADO_REQUERIDO);
        ValidationsUtility.lenghtString(clienteDTO.getEstado(), 1, ClienteServiceMessages.ESTADO_LENGHT);
        ValidationsUtility.integerIsNullOrLessZero(clienteDTO.getTipoDocumentoId(), ClienteServiceMessages.TIPO_DOCUMENTO_ID_REQUERIDO);

    }
}
