package app_be_mifac.restcontroller.seguridad;


import app_be_mifac.dto.UsuarioDTO;
import app_be_mifac.entity.seguridad.Usuario;
import app_be_mifac.repository.seguridad.UsuarioRepository;
import app_be_mifac.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;

import java.util.List;

import static app_be_mifac.constants.Constants.API_USUARIO;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(API_USUARIO)
public class AdminUsuarioController {

    private final ModelMapper modelMapper=new ModelMapper();
    
    private final UsuarioService usuarioService;
    
    
    public AdminUsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}


	@Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    Page<Usuario> index(@PageableDefault(sort = "nombres", size = 5) Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }
    
	@GetMapping("/by-email")
	public ResponseEntity<?> findByLikeRazonSocial(@RequestParam String email){
		try {
			
			Usuario usuario= new Usuario();
			usuario.setEmail(email);
			List<Usuario> usuarios= usuarioService.findByLikeObject(usuario);
			if (usuarios.isEmpty()) {
				return ResponseEntity.noContent().build();				
			} 
			return  ResponseEntity.ok(usuarios);
		} catch (Exception e) {
			//log.error(e.getMessage(),e);
			return ResponseEntity.internalServerError().build();
		}
	}

    @GetMapping("/listar")
    List<Usuario> listar() {
        return usuarioRepository.findAll();
    }


    @GetMapping("/{id}")
    Usuario obtener(@PathVariable Integer id) {
        return usuarioRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Usuario crear(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioRepository.save(usuario);
        return usuario;
    }


    @PutMapping("/{id}")
    Usuario actualizar(
            @PathVariable Integer id,
            @RequestBody UsuarioDTO usuarioDTO
    ) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        modelMapper.map(usuarioDTO, usuario);

        return usuario;
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        usuarioRepository.delete(usuario);
    }
}
