package dominio.controladores;

import java.util.List;

// Clase GestorRepartos
public class GestorRepartos {

    // Funcionalidad para marcar un pedido como recogido
    public void marcarPedidoRecogido(ServicioEntrega servicio) {
        servicio.setRecogido(true);
        System.out.println("Pedido recogido: " + servicio.getIdPedido());
    }

    // Funcionalidad para marcar un pedido como entregado
    public void marcarPedidoEntregado(ServicioEntrega servicio) {
        if (servicio.isRecogido()) {
            servicio.setEntregado(true);
            System.out.println("Pedido entregado: " + servicio.getIdPedido());
        } else {
            System.out.println("Error: El pedido no ha sido recogido aún.");
        }
    }

    // Funcionalidad para registrar un nuevo repartidor
    public Repartidor registrarRepartidor(String nombre, String apellidos, String nif, List<CodigoPostal> zonas) {
        Repartidor nuevoRepartidor = new Repartidor(nombre, apellidos, nif, zonas);
        System.out.println("Repartidor registrado: " + nombre + " " + apellidos);
        return nuevoRepartidor;
    }
}