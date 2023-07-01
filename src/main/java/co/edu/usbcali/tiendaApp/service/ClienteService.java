package co.edu.usbcali.tiendaApp.service;

import co.edu.usbcali.tiendaApp.domain.Cliente;
import co.edu.usbcali.tiendaApp.dto.ClienteDTO;
import co.edu.usbcali.tiendaApp.dto.ProductoDTO;
import co.edu.usbcali.tiendaApp.request.CrearClienteRequest;
import co.edu.usbcali.tiendaApp.response.CrearClienteResponse;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> obtenerTodos();
    Cliente buscarClientePorId(Integer id) throws Exception;

    ClienteDTO guardar(ClienteDTO clienteDTO) throws Exception;
    ClienteDTO actualizar(ClienteDTO clienteDTO) throws Exception;
    CrearClienteResponse crearCliente(CrearClienteRequest crearClienteRequest) throws Exception;
    List<ClienteDTO> buscarPorNombresLike(String nombre) throws Exception;



}
