package co.edu.usbcali.tiendaApp.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearCategoriaResponse {
    private Integer id;
    private String nombre;
    private String descripcion;
}
