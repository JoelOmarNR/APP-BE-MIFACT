package app_be_mifac.repository.seguridad;

import app_be_mifac.entity.seguridad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByEmail(String email);
	boolean existsByEmail(String email);

	
	@Query("select p from Usuario p where upper(p.email) like concat('%', upper(:email), '%')")
	List<Usuario> findByLikeEmail(@Param("email") String email);

		
}
