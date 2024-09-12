package app_be_mifac.entity.seguridad.vm;

import lombok.Data;

@Data
public class AuthCredentials {
    private String username;
    private String password;
}
