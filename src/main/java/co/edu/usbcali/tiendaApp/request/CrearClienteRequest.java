package co.edu.usbcali.tiendaApp.request;

import co.edu.usbcali.tiendaApp.util.messages.ClienteServiceMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearClienteRequest {

    @NotBlank(message = ClienteServiceMessages.NOMBRES_REQUERIDOS)
    private String nombres;

    @NotBlank(message = ClienteServiceMessages.APELLIDOS_REQUERIDOS)
    private String apellidos;

    @NotBlank(message = ClienteServiceMessages.DOCUMENTO_REQUERIDO)
    private String documento;

    @NotBlank(message = ClienteServiceMessages.ESTADO_REQUERIDO)
    @Length(max = 1, message = ClienteServiceMessages.ESTADO_LENGHT)
    private String estado;

    @NotNull(message = ClienteServiceMessages.TIPO_DOCUMENTO_ID_REQUERIDO)
    private Integer tipoDocumentoId;


}
