package app_be_mifac.validation;

import lombok.Data;

@Data
public class ValidationError {
    private String code;
    private String message;

}
