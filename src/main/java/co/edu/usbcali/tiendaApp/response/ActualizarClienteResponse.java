package co.edu.usbcali.tiendaApp.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarClienteResponse {

    private String nombres;
    private String apellidos;
    private String documento;
    private String estado;
    private String tipoDocumentoDescripcion;
}
