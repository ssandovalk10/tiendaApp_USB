package co.edu.usbcali.tiendaApp.request;

import co.edu.usbcali.tiendaApp.service.ProductoService;
import co.edu.usbcali.tiendaApp.util.messages.ProductoServiceMessages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearProductoRequest {
    @NotNull(message = ProductoServiceMessages.REFERENCIA_REQUERIDA)
    private String referencia;
    @NotNull(message = ProductoServiceMessages.NOMBRE_REQUERIDO)
    private String nombre;
    private String descripcion;

    @NotNull(message = ProductoServiceMessages.UNIDADES_DISPONIBLES_REQUERIDO)
    @Min(value=0, message = ProductoServiceMessages.UNIDADES_DISPONIBLES_NUMERO)
    private BigDecimal unidadesDisponibles;

    @NotNull(message = ProductoServiceMessages.PRECIO_UNITARIO_REQUERIDO)
    @Min(value=0, message = ProductoServiceMessages.PRECIO_UNITARIO_REQUERIDO)
    private BigDecimal precioUnitario;
    private Integer categoriaId;

}
