package icedLatteFood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import icedLatteFood.persistencia.RestauranteDAO;
import icedLatteFood.dominio.entidades.Direccion;
import icedLatteFood.dominio.entidades.Restaurante;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RestauranteDAO restauranteDAO;

    @Override
    public void run(String... args) throws Exception {
        // Crear direcciones de prueba
        Direccion direccion1 = new Direccion("Calle Falsa", 123," ", 28042, "Madrid");
        Direccion direccion2 = new Direccion("Avenida choices", 742, " ",28004, "Madrid");

        // Crear restaurantes de prueba
        Restaurante restaurante1 = new Restaurante("pass123", "The big big beat", "C12345678", false, direccion1);
        Restaurante restaurante2 = new Restaurante("pass456", "hot to go!", "B2346789", true, direccion2);

        // Guardar restaurantes en la base de datos
        restauranteDAO.save(restaurante1);
        restauranteDAO.save(restaurante2);
    }
}
