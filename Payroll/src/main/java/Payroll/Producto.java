package Payroll;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Producto {

    private @Id
    @GeneratedValue Long id;

    private String nombre;
    private double precio;
    private int cantidad;
    private String categoria;

    Producto() {
    }

    Producto(String nombre, double precio, int cantidad, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof Producto))
            return false;

        Producto producto = (Producto) o;

        return Objects.equals(this.id, producto.id)
                && Objects.equals(this.nombre, producto.nombre)
                && this.precio == producto.precio
                && this.cantidad == producto.cantidad
                && Objects.equals(this.categoria, producto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.id,
                this.nombre,
                this.precio,
                this.cantidad,
                this.categoria
        );
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + this.id +
                ", nombre='" + this.nombre + '\'' +
                ", precio=" + this.precio +
                ", cantidad=" + this.cantidad +
                ", categoria='" + this.categoria + '\'' +
                '}';
    }
}