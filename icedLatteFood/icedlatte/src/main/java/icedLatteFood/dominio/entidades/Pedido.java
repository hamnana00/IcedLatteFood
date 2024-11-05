package icedLatteFood.dominio.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Pedido {
    private int pedidoId; // Atributo para almacenar el ID del pedido
    private Cliente cliente; // Cliente que realiza el pedido
    private Restaurante restaurante; // Restaurante donde se realiza el pedido
    private ServicioEntrega entrega; // Servicio de entrega asociado al pedido
    private Date fecha; // Fecha en que se realizó el pedido
    private List<ItemMenu> items; // Lista de items del menú en el pedido
    private EstadoPedido estado; // Estado actual del pedido
    private Pago pago; // Información sobre el pago realizado para el pedido
    private double precioTotal; // Atributo para almacenar el precio total del pedido

    // Constructor que inicializa la fecha del pedido y crea la lista de items
    public Pedido(int idPed, String idCli, String nombre, String origen, int destino, double precioTotal, Date fecha, int idRepar) {
        this.fecha = fecha;
        this.items = new ArrayList<>(); // Inicializa la lista de items como una nueva ArrayList
        this.estado = EstadoPedido.PEDIDO; // Establece el estado inicial del pedido como PEDIDO
        this.precioTotal = precioTotal; // Establece el precio total inicial
    }

    // Getter para obtener el ID del pedido
    public int getId() {
        return pedidoId; // Retorna el ID del pedido
    }

    // Método para establecer el ID del pedido, con validación para asegurar que sea positivo
    public void setId(int pedidoId) {
        if (pedidoId > 0) { // Validación del ID
            this.pedidoId = pedidoId; // Asigna el ID del pedido
        } else {
            throw new IllegalArgumentException("El ID del pedido debe ser positivo"); // Lanza excepción si el ID no es válido
        }
    }

    // Getter para obtener la fecha del pedido
    public Date getFecha() {
        return fecha; // Retorna la fecha del pedido
    }

    // Setter para establecer la fecha del pedido
    public void setFecha(Date fecha) {
        this.fecha = fecha; // Asigna la nueva fecha al pedido
    }

    // Getter para obtener la lista de items del pedido
    public List<ItemMenu> getItems() {
        return items; // Retorna la lista de items del pedido
    }

    // Método para añadir un item al pedido
    public void addItem(ItemMenu item) {
        if (item != null) { // Verifica que el item no sea nulo
            this.items.add(item); // Agrega el item a la lista de items
        } else {
            throw new IllegalArgumentException("El item no puede ser nulo"); // Lanza excepción si el item es nulo
        }
    }

    // Método para eliminar un item del pedido
    public void deleteItem(ItemMenu item) {
        if (item != null) { // Verifica que el item no sea nulo
            this.items.remove(item); // Elimina el item de la lista de items
        } else {
            throw new IllegalArgumentException("El item a eliminar no puede ser nulo"); // Lanza excepción si el item es nulo
        }
    }

    // Método para calcular el precio total del pedido
    public double getPrecioTotal() {
        double total = 0.0; // Inicializa el total a 0
        for (ItemMenu item : items) { // Itera a través de cada item en la lista de items
            total += item.getPrecio(); // Suma el precio de cada item al total
        }
        return total; // Retorna el total calculado
    }

    // Getter para obtener el estado del pedido
    public EstadoPedido getEstado() {
        return estado; // Retorna el estado del pedido
    }

    // Método para establecer el estado del pedido, con validación
    public void setEstado(EstadoPedido estado) {
        if (estado != null) { // Verifica que el estado no sea nulo
            this.estado = estado; // Asigna el nuevo estado al pedido
        } else {
            throw new IllegalArgumentException("El estado no puede ser nulo"); // Lanza excepción si el estado es nulo
        }
    }

    // Getter para obtener la información del pago
    public Pago getPago() {
        return pago; // Retorna la información sobre el pago
    }

    // Método para establecer la información del pago
    public void setPago(Pago pago) {
        this.pago = pago; // Asigna la información del pago al pedido
    }

    // Método para obtener el nombre del cliente que realizó el pedido
    public String getNombre() {
        return cliente != null ? cliente.getNombre() : null; // Retorna el nombre del cliente si existe
    }

    // Método para obtener el cliente asociado al pedido
    public Cliente getCliente() {
        return cliente; // Retorna el objeto Cliente asociado al pedido
    }

    // Método para obtener el origen del pedido (nombre del restaurante)
    public String getOrigen() {
        return restaurante != null ? restaurante.getNombre() : null; // Retorna el nombre del restaurante si existe
    }

    // Método para obtener el destino del pedido (dirección de entrega)
    public String getDestino() {
        return entrega != null ? String.valueOf(entrega.getDireccion()) : null; // Retorna la dirección de entrega si existe
    }

    // Método para obtener la hora del pedido en formato "HH:mm:ss"
    public String getHora() {
        // Formato de la hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime localDateTime = new java.sql.Timestamp(fecha.getTime()).toLocalDateTime(); // Convierte la fecha a LocalDateTime
        return localDateTime.format(formatter); // Retorna la hora formateada
    }

    // Método para obtener el ID del repartidor
    public int getRepartidorId() {
        if (entrega != null) { // Verifica que el servicio de entrega no sea nulo
            return entrega.getRepartidorId(); // Retorna el ID del repartidor desde el servicio de entrega
        } else {
            throw new IllegalStateException("No hay servicio de entrega asociado al pedido."); // Manejo de error si no hay entrega
        }
    }

    // Método para obtener una representación en cadena del pedido
    @Override
    public String toString() {
        return "Pedido [pedidoId=" + pedidoId + ", fecha=" + fecha + ", items=" + items +
                ", estado=" + estado + ", pago=" + (pago != null ? pago.toString() : "sin pago") +
                ", precioTotal=" + precioTotal + "]"; // Incluye el precio total en la representación
    }

    // Método para eliminar un item del pedido (similar a deleteItem)
    public void removeItem(ItemMenu item) {
        if (item != null) { // Verifica que el item no sea nulo
            this.items.remove(item); // Elimina el item de la lista de items
        } else {
            throw new IllegalArgumentException("El item a eliminar no puede ser nulo"); // Lanza excepción si el item es nulo
        }
    }

    // Método para establecer el cliente que realiza el pedido
    public void setCliente(Cliente cliente) {
        if (cliente != null) { // Verifica que el cliente no sea nulo
            this.cliente = cliente; // Asigna el cliente al pedido
        } else {
            throw new IllegalArgumentException("El cliente no puede ser nulo"); // Lanza excepción si el cliente es nulo
        }
    }

    // Método para establecer el restaurante donde se realiza el pedido
    public void setRestaurante(Restaurante restaurante) {
        if (restaurante != null) { // Verifica que el restaurante no sea nulo
            this.restaurante = restaurante; // Asigna el restaurante al pedido
        } else {
            throw new IllegalArgumentException("El restaurante no puede ser nulo"); // Lanza excepción si el restaurante es nulo
        }
    }

    // Método para establecer el servicio de entrega
    public void setEntrega(ServicioEntrega entrega) {
        if (entrega != null) {
            this.entrega = entrega; // Asigna el servicio de entrega al pedido
        } else {
            throw new IllegalArgumentException("El servicio de entrega no puede ser nulo"); // Lanza excepción si el servicio es nulo
        }
    }

    // Método para avanzar el estado del pedido a su siguiente etapa
    public void avanzarEstado() {
        switch (estado) { // Cambia el estado según el estado actual
            case PEDIDO:
                setEstado(EstadoPedido.PAGADO); // Si el estado es PEDIDO, lo cambia a PAGADO
                break;
            case PAGADO:
                setEstado(EstadoPedido.RECOGIDO); // Si el estado es PAGADO, lo cambia a RECOGIDO
                break;
            case RECOGIDO:
                setEstado(EstadoPedido.ENTREGADO); // Si el estado es RECOGIDO, lo cambia a ENTREGADO
                break;
            default:
                throw new IllegalStateException("El estado del pedido ya está en 'ENTREGADO'."); // Manejo de error si ya está entregado
        }
    }

    // Método para establecer el precio total del pedido
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal; // Asigna el nuevo precio total
    }

    // Método para obtener la dirección de entrega
    public Direccion getDireccionEntrega() {
        return entrega != null ? entrega.getDireccion() : null; // Retorna la dirección de entrega desde el servicio de entrega
    }
}
