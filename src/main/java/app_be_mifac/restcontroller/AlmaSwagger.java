package app_be_mifac.restcontroller;

import app_be_mifac.entity.Producto;
import app_be_mifac.response.HttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static app_be_mifac.constants.Constants.API_PRODUCTO;

/**
 * @author JOEL OMAR NR
 * @version 1.0
 * @since 11/09/2024
 */

@RequestMapping(API_PRODUCTO)
public interface AlmaSwagger {

    @Operation(
            summary = "Listar productos",
            description = "Obtiene lista de productos.",
            tags = { "producto-controller" }

    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping("/list")
    public ResponseEntity<HttpResponse> getProductos(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size);


    @Operation(
            summary = "Crear productos",
            description = "Registra productos en la DB.",
            tags = { "producto-controller" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATE", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createProducto(@RequestBody Producto producto);

    @Operation(
            summary = "Buscar producto",
            description = "Buscar producto por Id",
            tags = { "producto-controller" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<HttpResponse> getProducto(@PathVariable("id") Long id);


    @Operation(
            summary = "Buscar producto",
            description = "Buscar producto por Id",
            tags = { "producto-controller" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping("/search")
    public ResponseEntity<HttpResponse> searchProducto(@RequestParam Optional<String> nombre, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size);


    @Operation(
            summary = "Actualizar producto",
            description = "Editar productos",
            tags = { "producto-controller" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PutMapping("/update")
    public ResponseEntity<HttpResponse> updateProducto(@RequestBody Producto producto);


    @Operation(
            summary = "Eliminar producto",
            description = "Eliminar producto por Id",
            tags = { "producto-controller" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteProducto(@PathVariable("id") Long id);
}
