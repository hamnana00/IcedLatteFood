package icedLatteFood.dominio.entidades;
import java.util.*;
public class Pedido {
    private Cliente cliente;
    private Restaurante restaurante;
    private dominio.entidades.ServicioEntrega entrega;
    private Date fecha;
    private List<ItemMenu> items;
    private EstadoPedido estado;
    private Pago pago;

    public Pedido(Date fecha){
        this.fecha = fecha;
        this.items = new ArrayList<>();
        this.estado = EstadoPedido.PEDIDO;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<ItemMenu> getItems() {
        return items;
    }
    public void addItem(ItemMenu item){
        this.items.add(item);
    }
    public void deleteItem(ItemMenu item){
        this.items.remove(item);
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
    public String toString(){
        return "Pedido [fecha=" + fecha + ", items=" + items + ", estado=" + estado + ", pago=" + pago + "]";
    }
}