package co.edu.usbcali.tiendaApp.service;

import co.edu.usbcali.tiendaApp.domain.Categoria;
import co.edu.usbcali.tiendaApp.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> obtenerTodos();

    CategoriaDTO buscarPorId(Integer id) throws Exception;

    Categoria buscarCategoriaPorId(Integer id) throws Exception;

    CategoriaDTO guardar(CategoriaDTO categoriaDTO) throws Exception;

    CategoriaDTO actualizar(CategoriaDTO categoriaDTO) throws Exception;
    public String eliminarById(Integer id) throws Exception;

    List<CategoriaDTO> buscarPorNombreLike(String nombre) throws Exception;
}
