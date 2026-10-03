package Payroll;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductoController {

    private final ProductoRepository repository;

    ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    // GET - Lista todos los productos
    @GetMapping("/productos")
    List<Producto> all() {
        return repository.findAll();
    }

    // POST - Crea un nuevo producto
    @PostMapping("/productos")
    Producto nuevoProducto(@RequestBody Producto nuevoProducto) {
        return repository.save(nuevoProducto);
    }

    // GET - Busca un producto por ID
    @GetMapping("/productos/{id}")
    Producto one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    // PUT - Actualiza un producto existente
    @PutMapping("/productos/{id}")
    Producto reemplazarProducto(@RequestBody Producto nuevoProducto, @PathVariable Long id) {

        return repository.findById(id)
                .map(producto -> {

                    producto.setNombre(nuevoProducto.getNombre());
                    producto.setPrecio(nuevoProducto.getPrecio());
                    producto.setCantidad(nuevoProducto.getCantidad());
                    producto.setCategoria(nuevoProducto.getCategoria());

                    return repository.save(producto);

                })
                .orElseGet(() -> {

                    nuevoProducto.setId(id);

                    return repository.save(nuevoProducto);
                });
    }

    // DELETE - Elimina un producto
    @DeleteMapping("/productos/{id}")
    void eliminarProducto(@PathVariable Long id) {
        repository.deleteById(id);
    }
}