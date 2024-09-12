package app_be_mifac.service;

import app_be_mifac.entity.Producto;
import app_be_mifac.generico.GenericoService;
import org.springframework.data.domain.Page;

/**
 * @author JOEL OMAR NR
 * @version 1.0
 * @since 11/09/2024
 */
public interface ProductoService extends GenericoService<Producto> {

    // Producto functions
    Producto createProducto(Producto producto);
    Producto updateProducto(Producto producto);
    Page<Producto> getProductos(int page, int size);
    Iterable<Producto> getProductos();
    Producto getProducto(Long id);
    Page<Producto> searchProductos(String nombre, int page, int size);

    boolean deleteProducto(Long id);
}
