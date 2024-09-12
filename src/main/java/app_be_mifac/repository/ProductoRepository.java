package app_be_mifac.repository;

import app_be_mifac.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JOEL OMAR NR
 * @version 1.0
 * @since 11/09/2024
 */

@Repository
public interface ProductoRepository extends PagingAndSortingRepository<Producto, Long>,JpaRepository<Producto, Long>{

    Page<Producto> findByNombreContaining(String nombre, Pageable pageable);

    @Query("select p from Producto p where upper(p.nombre) like upper(:nombre)")
    List<Producto> findByLikeNombre(@Param("nombre") String nombre);
}
