package co.edu.usbcali.tiendaApp.service.impl;

import co.edu.usbcali.tiendaApp.domain.Cliente;
import co.edu.usbcali.tiendaApp.domain.TipoDocumento;
import co.edu.usbcali.tiendaApp.dto.ClienteDTO;
import co.edu.usbcali.tiendaApp.exceptions.ClienteException;
import co.edu.usbcali.tiendaApp.mapper.ClienteMapper;
import co.edu.usbcali.tiendaApp.repository.ClienteRepository;
import co.edu.usbcali.tiendaApp.repository.TipoDocumentoRepository;
import co.edu.usbcali.tiendaApp.request.ActualizarClienteRequest;
import co.edu.usbcali.tiendaApp.request.CrearClienteRequest;
import co.edu.usbcali.tiendaApp.response.ActualizarClienteResponse;
import co.edu.usbcali.tiendaApp.response.CrearClienteResponse;
import co.edu.usbcali.tiendaApp.response.ListarClientesResponse;
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
    public List<ListarClientesResponse> obtenerTodos() {

        return  ClienteMapper.domainToResponseList(clienteRepository.findAll());

    }

    @Override
    public ClienteDTO buscarPorId(Integer id) throws Exception {
        ValidationsUtility.integerIsNullOrLessZero(id, ClienteServiceMessages.ID_VALIDO_MSG);

        //lANDAS
        return clienteRepository.findById(id).map(ClienteMapper::domainToDto).orElseThrow(
                () -> new ClienteException(String.format(ClienteServiceMessages.CLIENTE_NO_ENCONTRADO_POR_ID, id)));

    }

    public CrearClienteResponse crearCliente(CrearClienteRequest crearClienteRequest) throws Exception {

        Cliente cliente = ClienteMapper.crearRequestToDomain(crearClienteRequest);

        TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoPorId(
                crearClienteRequest.getTipoDocumentoId());

        boolean existePorTipoYDocumento = clienteRepository.existsByTipoDocumentoIdAndDocumento(
                crearClienteRequest.getTipoDocumentoId(), crearClienteRequest.getDocumento());

        if(existePorTipoYDocumento) throw new Exception(
                String.format(ClienteServiceMessages.EXISTE_POR_TIPO_DOCUMENTO_Y_DOCUMENTO,
                        tipoDocumento.getDescripcion(), crearClienteRequest.getDocumento())
        );

        cliente.setTipoDocumento(tipoDocumento);
        return ClienteMapper.crearDomainToResponse(clienteRepository.save(cliente));
    }

    @Override
    public ActualizarClienteResponse actualizarCliente(ActualizarClienteRequest actualizarClienteRequest) throws Exception {

        TipoDocumento tipoDocumento = tipoDocumentoService.buscarTipoDocumentoPorId(
                actualizarClienteRequest.getTipoDocumentoId());

        Cliente clienteUpdate = clienteRepository.findById(actualizarClienteRequest.getId()).orElse(null);;
        if (clienteUpdate != null) {
            clienteUpdate.setNombres(actualizarClienteRequest.getNombres());
            clienteUpdate.setApellidos(actualizarClienteRequest.getApellidos());
            clienteUpdate.setEstado(actualizarClienteRequest.getEstado());
            clienteUpdate.setDocumento(actualizarClienteRequest.getDocumento());
            clienteUpdate.setTipoDocumento(tipoDocumento);
        }

        return ClienteMapper.actualizarClienteResponse(clienteRepository.save(clienteUpdate));
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
