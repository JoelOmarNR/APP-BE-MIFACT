package app_be_mifac.validation;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DTO de un formato estándar de error genérico.
 */
@Data
public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private long timeStamp;

    /**
     * un mapa de errores de validación, si es que corresponde.
     * Ej.: "slug": [
     *  {"code": "NotNull", "message": "El slug es obligatorio."},
     *  {"code": "Pattern", "message": "El slug no cumple con el formato requerido."}
     * ]
     */
    private Map<String, List<ValidationError>> errors = new HashMap<>();

}
