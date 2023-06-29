package co.edu.usbcali.tiendaApp.service;

import co.edu.usbcali.tiendaApp.domain.Categoria;
import co.edu.usbcali.tiendaApp.dto.CategoriaDTO;
import co.edu.usbcali.tiendaApp.request.CrearCategoriaRequest;
import co.edu.usbcali.tiendaApp.response.CrearCategoriaResponse;

import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> obtenerTodos();

    CategoriaDTO buscarPorId(Integer id) throws Exception;

    Categoria buscarCategoriaPorId(Integer id) throws Exception;

    CategoriaDTO guardar(CategoriaDTO categoriaDTO) throws Exception;

    CategoriaDTO actualizar(CategoriaDTO categoriaDTO) throws Exception;
    public String eliminarById(Integer id) throws Exception;

    List<CategoriaDTO> buscarPorNombreLike(String nombre) throws Exception;

    CrearCategoriaResponse crearCategoria(CrearCategoriaRequest crearCategoriaRequest) throws Exception;
}
