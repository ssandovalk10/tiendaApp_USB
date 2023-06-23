package co.edu.usbcali.tiendaApp.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearProductoResponse {
    private Integer id;
    private String referencia;
    private String nombre;
    private String descripcion;
    private BigDecimal precioUnitario;
    private BigDecimal unidadesDisponibles;
    private String nombreCategoria;
}
