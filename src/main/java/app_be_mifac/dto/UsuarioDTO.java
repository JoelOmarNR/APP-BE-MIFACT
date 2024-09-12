package app_be_mifac.dto;


import app_be_mifac.entity.seguridad.Usuario;
import lombok.Data;


@Data
public class UsuarioDTO {
    private String nombres;
    private String apellidos;
    private String nombreCompleto;
    private String email;
    private String status;
    private String password;
    private Usuario.Rol rol;
    

}
