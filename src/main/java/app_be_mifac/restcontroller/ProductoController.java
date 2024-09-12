package app_be_mifac.restcontroller;


import app_be_mifac.entity.Producto;
import app_be_mifac.response.HttpResponse;
import app_be_mifac.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author JOEL OMAR NR
 * @version 1.0
 * @since 11/09/2024
 */

@RestController
@RequiredArgsConstructor
public class ProductoController implements AlmaSwagger {
    private final ProductoService productoService;


    @Override
    public ResponseEntity<HttpResponse> getProductos(Optional<Integer> page, Optional<Integer> size) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", productoService.getProductos(page.orElse(0), size.orElse(10)));

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(data)
                        .message("Productos retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @Override
    public ResponseEntity<HttpResponse> createProducto(Producto producto) {
        Map<String, Object> data = new HashMap<>();
        data.put("producto", productoService.createProducto(producto));

        return ResponseEntity.created(URI.create(""))
                .body(
                        HttpResponse.builder()
                                .timeStamp(now().toString())
                                .data(data)
                                .message("Producto created")
                                .status(CREATED)
                                .statusCode(CREATED.value())
                                .build()
                );
    }

    @Override
    public ResponseEntity<HttpResponse> getProducto(Long id) {
        Map<String, Object> data = new HashMap<>();
        data.put("producto", productoService.getProducto(id));

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(data)
                        .message("Producto retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @Override
    public ResponseEntity<HttpResponse> searchProducto(Optional<String> nombre, Optional<Integer> page, Optional<Integer> size) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", productoService.searchProductos(nombre.orElse(""), page.orElse(0), size.orElse(10)));

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(data)
                        .message("Products retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @Override
    public ResponseEntity<HttpResponse> updateCustomer(Producto producto) {
        Map<String, Object> data = new HashMap<>();
        data.put("producto", productoService.updateProducto(producto));

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(data)
                        .message("Product updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @Override
    public ResponseEntity<HttpResponse> deleteProducto(Long id) {
        boolean isDeleted = productoService.deleteProducto(id);

        Map<String, Object> data = new HashMap<>();
        data.put("deleted", isDeleted);

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(data)
                        .message(isDeleted ? "Product deleted" : "Product not found")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }



}
