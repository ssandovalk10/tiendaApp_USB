package co.edu.usbcali.tiendaApp.controller;
import co.edu.usbcali.tiendaApp.dto.TipoDocumentoDTO;
import co.edu.usbcali.tiendaApp.service.TipoDocumentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/tipoDocumento")
public class TipoDocumentoController {
    private final TipoDocumentoService tipoDocumentoService;


    public TipoDocumentoController(TipoDocumentoService tipoDocumentoService){
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @PostMapping(value = "/nuevoTipoDocumento")
    TipoDocumentoDTO nuevoTipoDocumento(@RequestBody @Valid TipoDocumentoDTO tipoDocumentoDTO) throws Exception {
       return  tipoDocumentoService.guardar(tipoDocumentoDTO);
    }

    @GetMapping("/buscarTodos")
    List<TipoDocumentoDTO> buscarTodos(){
        return tipoDocumentoService.obtenerTodos();
    }

    @GetMapping("/buscarPorId/")
    ResponseEntity<TipoDocumentoDTO> buscarPorId(@RequestParam Integer id) throws Exception {
        return new ResponseEntity<TipoDocumentoDTO>(tipoDocumentoService.buscarPorId(id),
                HttpStatus.OK);
    }

    @PutMapping(value = "/actualizarTipoDocumento")
    TipoDocumentoDTO actualizarTipoDocumento(@RequestBody TipoDocumentoDTO tipoDocumentoDTO) throws Exception {
        return tipoDocumentoService.actualizar(tipoDocumentoDTO);
    }


}
