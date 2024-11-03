package icedLatteFood.dominio.entidades;

// Clase Restaurante
public class Restaurante {
    private String nombre;
    private String cif;
    private boolean favorito;  // Atributo para saber si el restaurante es favorito
    private Direccion direccion; // Atributo para la dirección del restaurante
    private CartaMenu cartaMenu;  // Ahora es un objeto CartaMenu

    // Constructor
    public Restaurante(String nombre, String cif, boolean favorito, Direccion direccion) {
        this.nombre = nombre;
        this.cif = cif;
        this.favorito = favorito; // Ahora inicializamos favorito
        this.direccion = direccion;
        this.cartaMenu = new CartaMenu(); // Inicializar cartaMenu aquí
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public CartaMenu getCartaMenu() {
        return cartaMenu;
    }

    // Métodos para interactuar con la carta de menú
    public void agregarItemMenu(ItemMenu itemMenu) {
        cartaMenu.agregarItemMenu(itemMenu);
    }

    public void eliminarItemMenu(String nombreItem) {
        cartaMenu.eliminarItemMenu(nombreItem);
    }

    public void mostrarMenu() {
        cartaMenu.mostrarMenu();
    }
}
