package persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Clase RestauranteDAO
public class RestauranteDAO {

    // Simulamos una base de datos con una lista de restaurantes
    private List<Restaurante> restaurantes;

    // Constructor
    public RestauranteDAO() {
        this.restaurantes = new ArrayList<>();
        // Aquí podríamos inicializar con datos de ejemplo o hacer una conexión a una BD real
    }

    // Funcionalidad para seleccionar restaurantes por código postal
    public List<Restaurante> selectPorCodigoPostal(CodigoPostal codigoPostal) {
        return restaurantes.stream()
                .filter(restaurante -> restaurante.getDireccion().getCodigoPostal().equals(codigoPostal))
                .collect(Collectors.toList());
    }

    // Funcionalidad para seleccionar restaurantes por código postal y texto libre (nombre del restaurante)
    public List<Restaurante> selectPorCodigoPostalYTextoLibre(CodigoPostal codigoPostal, String texto) {
        return restaurantes.stream()
                .filter(restaurante -> restaurante.getDireccion().getCodigoPostal().equals(codigoPostal) &&
                        restaurante.getNombre().toLowerCase().contains(texto.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Método para añadir un restaurante (puedes usarlo para poblar la lista de restaurantes)
    public void agregarRestaurante(Restaurante restaurante) {
        restaurantes.add(restaurante);
    }
}