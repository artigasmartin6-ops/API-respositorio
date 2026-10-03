const API_URL = "/productos";

// Cargar productos al abrir la página
document.addEventListener("DOMContentLoaded", cargarProductos);

// GET - Obtener todos los productos
function cargarProductos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(productos => {
            const tabla = document.getElementById("tablaProductos");

            tabla.innerHTML = "";

            productos.forEach(producto => {
                const fila = document.createElement("tr");

                fila.innerHTML = `
                    <td>${producto.id}</td>
                    <td>${producto.nombre}</td>
                    <td>${producto.precio}</td>
                    <td>${producto.cantidad}</td>
                    <td>${producto.categoria}</td>
                    <td>
                        <button onclick="editarProducto(
                            ${producto.id},
                            '${producto.nombre}',
                            ${producto.precio},
                            ${producto.cantidad},
                            '${producto.categoria}'
                        )">
                            Editar
                        </button>

                        <button onclick="eliminarProducto(${producto.id})">
                            Eliminar
                        </button>
                    </td>
                `;

                tabla.appendChild(fila);
            });
        });
}

// POST o PUT
function guardarProducto() {

    const id = document.getElementById("idProducto").value;
    const nombre = document.getElementById("nombre").value;
    const precio = document.getElementById("precio").value;
    const cantidad = document.getElementById("cantidad").value;
    const categoria = document.getElementById("categoria").value;

    const producto = {
        nombre: nombre,
        precio: parseFloat(precio),
        cantidad: parseInt(cantidad),
        categoria: categoria
    };

    // Si no hay ID, crea un producto nuevo
    if (id === "") {

        fetch(API_URL, {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(producto)
        })
        .then(() => {
            limpiarFormulario();
            cargarProductos();
        });

    } else {

        // Si hay ID, actualiza el producto
        fetch(`${API_URL}/${id}`, {
            method: "PUT",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(producto)
        })
        .then(() => {
            limpiarFormulario();
            cargarProductos();
        });
    }
}

// Coloca los datos del producto en el formulario
function editarProducto(id, nombre, precio, cantidad, categoria) {

    document.getElementById("idProducto").value = id;
    document.getElementById("nombre").value = nombre;
    document.getElementById("precio").value = precio;
    document.getElementById("cantidad").value = cantidad;
    document.getElementById("categoria").value = categoria;
}

// DELETE
function eliminarProducto(id) {

    fetch(`${API_URL}/${id}`, {
        method: "DELETE"
    })
    .then(() => {
        cargarProductos();
    });
}

// Limpia el formulario
function limpiarFormulario() {

    document.getElementById("idProducto").value = "";
    document.getElementById("nombre").value = "";
    document.getElementById("precio").value = "";
    document.getElementById("cantidad").value = "";
    document.getElementById("categoria").value = "";
}