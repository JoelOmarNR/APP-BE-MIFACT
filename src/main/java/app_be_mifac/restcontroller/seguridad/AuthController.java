package app_be_mifac.restcontroller.seguridad;

import app_be_mifac.entity.seguridad.Usuario;
import app_be_mifac.exception.BadRequestException;
import app_be_mifac.repository.seguridad.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

import static app_be_mifac.constants.Constants.API_AUTH;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(API_AUTH)
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registrar")
    void registrarUsuario(@RequestBody @Validated Usuario usuario) {
        boolean emailYaExiste = usuarioRepository
                .existsByEmail(usuario.getEmail());

        if (emailYaExiste) {
            throw new BadRequestException("El email ya fue registrado para otro usuario.");
        }
        String password = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(password);
        usuario.setRol(Usuario.Rol.ADMIN);
        usuarioRepository.save(usuario);
    }

    @GetMapping("/verificar-email")
    Map<String, Boolean> verificarEmail(@RequestParam String email) {
        boolean emailYaExiste = usuarioRepository
                .existsByEmail(email);

        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", emailYaExiste);
        return result;
    }

}
