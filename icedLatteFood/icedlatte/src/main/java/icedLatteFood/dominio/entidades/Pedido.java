package icedLatteFood.dominio.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import jakarta.persistence.*;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedidoId; 

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    private Restaurante restaurante;
    
    @ManyToOne
    @JoinColumn(name = "idEntrega")
    private ServicioEntrega entrega;

    @Column
    private Date fecha; // Fecha en que se realizó el pedido

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idPedido")
    private List<ItemMenu> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPago")
    private Pago pago;

    @Column
    private double precioTotal; // Atributo para almacenar el precio total del pedido

    public Pedido(int idPed, Cliente cliente, Restaurante restaurante, ServicioEntrega entrega, double precioTotal, Date fecha) {
        this.cliente = cliente;
        this.restaurante = restaurante;
        this.entrega = entrega;
        this.fecha = fecha;
        this.items = new ArrayList<>();
        this.estado = EstadoPedido.PEDIDO;
        this.precioTotal = precioTotal;
    }
    public int getId() {
        return pedidoId; // Retorna el ID del pedido
    }
    public void setId(int pedidoId) {
        if (pedidoId > 0) { // Validación del ID
            this.pedidoId = pedidoId; // Asigna el ID del pedido
        } else {
            throw new IllegalArgumentException("El ID del pedido debe ser positivo"); // Lanza excepción si el ID no es válido
        }
    }
    public Date getFecha() {
        return fecha; // Retorna la fecha del pedido
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha; // Asigna la nueva fecha al pedido
    }
    public List<ItemMenu> getItems() {
        return items; // Retorna la lista de items del pedido
    }
    public void addItem(ItemMenu item) {
        if (item != null) { // Verifica que el item no sea nulo
            this.items.add(item); // Agrega el item a la lista de items
        } else {
            throw new IllegalArgumentException("El item no puede ser nulo"); // Lanza excepción si el item es nulo
        }
    }
    public void deleteItem(ItemMenu item) {
        if (item != null) { // Verifica que el item no sea nulo
            this.items.remove(item); // Elimina el item de la lista de items
        } else {
            throw new IllegalArgumentException("El item a eliminar no puede ser nulo"); // Lanza excepción si el item es nulo
        }
    }
    public double getPrecioTotal() {
        double total = 0.0; // Inicializa el total a 0
        for (ItemMenu item : items) { // Itera a través de cada item en la lista de items
            total += item.getPrecio(); // Suma el precio de cada item al total
        }
        return total; // Retorna el total calculado
    }
    public EstadoPedido getEstado() {
        return estado; // Retorna el estado del pedido
    }
    public void setEstado(EstadoPedido estado) {
        if (estado != null) { // Verifica que el estado no sea nulo
            this.estado = estado; // Asigna el nuevo estado al pedido
        } else {
            throw new IllegalArgumentException("El estado no puede ser nulo"); // Lanza excepción si el estado es nulo
        }
    }
    public Pago getPago() {
        return pago; // Retorna la información sobre el pago
    }
    public void setPago(Pago pago) {
        this.pago = pago; // Asigna la información del pago al pedido
    }
    public String getNombre() {
        return cliente != null ? cliente.getNombre() : null; // Retorna el nombre del cliente si existe
    }
    public Cliente getCliente() {
        return cliente; // Retorna el objeto Cliente asociado al pedido
    }
    public String getOrigen() {
        return restaurante != null ? restaurante.getNombre() : null; // Retorna el nombre del restaurante si existe
    }
    public String getHora() {
        // Formato de la hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime localDateTime = new java.sql.Timestamp(fecha.getTime()).toLocalDateTime(); // Convierte la fecha a LocalDateTime
        return localDateTime.format(formatter); // Retorna la hora formateada
    }
    @Override
    public String toString() {
        return "Pedido [pedidoId=" + pedidoId + ", fecha=" + fecha + ", items=" + items +
                ", estado=" + estado + ", pago=" + (pago != null ? pago.toString() : "sin pago") +
                ", precioTotal=" + precioTotal + "]"; // Incluye el precio total en la representación
    }
    public void removeItem(ItemMenu item) {
        if (item != null) { // Verifica que el item no sea nulo
            this.items.remove(item); // Elimina el item de la lista de items
        } else {
            throw new IllegalArgumentException("El item a eliminar no puede ser nulo"); // Lanza excepción si el item es nulo
        }
    }
    public void setCliente(Cliente cliente) {
        if (cliente != null) { // Verifica que el cliente no sea nulo
            this.cliente = cliente; // Asigna el cliente al pedido
        } else {
            throw new IllegalArgumentException("El cliente no puede ser nulo"); // Lanza excepción si el cliente es nulo
        }
    }
    public void setRestaurante(Restaurante restaurante) {
        if (restaurante != null) { // Verifica que el restaurante no sea nulo
            this.restaurante = restaurante; // Asigna el restaurante al pedido
        } else {
            throw new IllegalArgumentException("El restaurante no puede ser nulo"); // Lanza excepción si el restaurante es nulo
        }
    }
    public void setEntrega(ServicioEntrega entrega) {
        if (entrega != null) {
            this.entrega = entrega; // Asigna el servicio de entrega al pedido
        } else {
            throw new IllegalArgumentException("El servicio de entrega no puede ser nulo"); // Lanza excepción si el servicio es nulo
        }
    }
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
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal; // Asigna el nuevo precio total
    }

}
