package Payroll;

class ProductoNotFoundException extends RuntimeException {

    ProductoNotFoundException(Long id) {
        super("Producto no encontrado: " + id);
    }
}