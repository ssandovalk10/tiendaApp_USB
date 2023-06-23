package co.edu.usbcali.tiendaApp.service;


import co.edu.usbcali.tiendaApp.dto.CategoriaDTO;
import co.edu.usbcali.tiendaApp.mocks.CategoriaMockTest;
import co.edu.usbcali.tiendaApp.repository.CategoriaRepository;
import co.edu.usbcali.tiendaApp.service.impl.CategoriaServiceImpl;
import co.edu.usbcali.tiendaApp.util.messages.CategoriaServiceMessages;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CategoriaServiceImplTest {

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;


    @Test
    void obtenerTodasLasCategorias_OK() {
        given(categoriaRepository.findAllByOrderByNombreAsc()).willReturn(
                CategoriaMockTest.CATEGORIAS_LIST
        );

        List<CategoriaDTO> obtenerTodas = categoriaService.obtenerTodos();

        assertEquals(2, obtenerTodas.size());
        assertEquals(CategoriaMockTest.ID_UNO, obtenerTodas.get(0).getId());
    }

    @Test
    void buscarPorId_NOK() {
        Exception exception = assertThrows(Exception.class, () ->
                categoriaService.buscarPorId(2));

        String expectedMessage = String.format(
                CategoriaServiceMessages.CATEGORIA_NO_ENCONTRADA_POR_ID, 2);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void buscarPorId_OK() throws Exception {
        given(categoriaRepository.findById(1)).willReturn(
                Optional.of(CategoriaMockTest.CATEGORIA_UNO)
        );

        CategoriaDTO categoriaDTO = categoriaService.buscarPorId(1);

        assertNotNull(categoriaDTO);
        assertEquals(CategoriaMockTest.NOMBRE_UNO, categoriaDTO.getNombre());
    }

    void guardar_OK() throws Exception {
        //
    }

}