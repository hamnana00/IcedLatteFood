package icedLatteFood.dominio.controladores;

import icedLatteFood.dominio.entidades.ServicioEntrega;
import icedLatteFood.dominio.entidades.Repartidor;
import icedLatteFood.dominio.entidades.CodigoPostal;
import java.util.List;

public class GestorRepartos {

    // Funcionalidad para marcar que un pedido ha sido recogido
    public void marcarPedidoRecogido(ServicioEntrega servicio) {
        if (servicio.getFechaRecepcion() == null) {
            servicio.registrarRecepcion();
            System.out.println("El pedido ha sido recogido. Fecha de recogida: " + servicio.getFechaRecepcion());
        } else {
            System.out.println("El pedido ya fue recogido previamente.");
        }
    }

    // Funcionalidad para marcar que un pedido ha sido entregado
    public void marcarPedidoEntregado(ServicioEntrega servicio) {
        if (servicio.getFechaEntrega() == null) {
            servicio.registrarEntrega();
            System.out.println("El pedido ha sido entregado. Fecha de entrega: " + servicio.getFechaEntrega());
        } else {
            System.out.println("El pedido ya fue entregado previamente.");
        }
    }

    // Funcionalidad para registrar un nuevo repartidor
    public Repartidor registrarRepartidor(String nombre, String apellidos, String nif, List<CodigoPostal> zonas) {
        Repartidor nuevoRepartidor = new Repartidor(nombre, apellidos, nif, 100); // Se puede inicializar con una eficiencia de 100, por ejemplo
        nuevoRepartidor.setZonas(zonas);
        System.out.println("Repartidor registrado: " + nombre + " " + apellidos);
        return nuevoRepartidor;
    }
}
