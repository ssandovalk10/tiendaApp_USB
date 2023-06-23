package co.edu.usbcali.tiendaApp.service;

import co.edu.usbcali.tiendaApp.dto.ClienteDTO;
import co.edu.usbcali.tiendaApp.request.ActualizarClienteRequest;
import co.edu.usbcali.tiendaApp.request.CrearClienteRequest;
import co.edu.usbcali.tiendaApp.response.ActualizarClienteResponse;
import co.edu.usbcali.tiendaApp.response.CrearClienteResponse;
import co.edu.usbcali.tiendaApp.response.ListarClientesResponse;

import java.util.List;

public interface ClienteService {

    List<ListarClientesResponse> obtenerTodos();
    ClienteDTO buscarPorId(Integer id) throws Exception;
    ActualizarClienteResponse actualizarCliente(ActualizarClienteRequest actualizarClienteRequest) throws Exception;
    CrearClienteResponse crearCliente(CrearClienteRequest crearClienteRequest) throws Exception;


}
