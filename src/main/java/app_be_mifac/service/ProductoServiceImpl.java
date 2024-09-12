package app_be_mifac.service;

import app_be_mifac.entity.Producto;
import app_be_mifac.exception.ServiceException;
import app_be_mifac.repository.ProductoRepository;
import app_be_mifac.util.BDUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import static org.springframework.data.domain.PageRequest.of;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> findByLikeObject(Producto producto) throws ServiceException {
        try {
            return productoRepository.findByLikeNombre(BDUtil.getLike(producto.getNombre()));
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Producto> findById(Long productoId) throws ServiceException {
        try {
            return productoRepository.findById(productoId);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public Producto save(Producto producto) throws ServiceException {
        return null;
    }

    @Override
    public Producto update(Producto producto) throws ServiceException {
        return null;
    }

    @Override
    public Boolean delete(Producto producto) throws ServiceException {
        return null;
    }

    @Override
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Page<Producto> getProductos(int page, int size) {
        return productoRepository.findAll(of(page,size));
    }

    @Override
    public Iterable<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProducto(Long id) {
        return productoRepository.findById(id).get();
    }

    @Override
    public Page<Producto> searchProductos(String nombre, int page, int size) {
        return productoRepository.findByNombreContaining(nombre, of(page,size));
    }

    @Override
    public boolean deleteProducto(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            productoRepository.delete(producto.get());
            return true;
        } else {
            return false;
        }
    }
}
