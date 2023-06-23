package co.edu.usbcali.tiendaApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Integer id;
    private String referencia;
    private String nombre;
    private String descripcion;
    private BigDecimal precioUnitario;
    private BigDecimal unidadesDisponibles;
    private Integer categoriaId;
}
