package icedLatteFood.dominio.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import icedLatteFood.persistencia.RestauranteDAO;
import icedLatteFood.dominio.entidades.Restaurante;

@Controller
public class RestauranteController {
    private static final Logger logger = LoggerFactory.getLogger(RestauranteController.class);

    @Autowired
    private RestauranteDAO restauranteDAO;

    @GetMapping
    public ResponseEntity<List<Restaurante>> getAllRestaurantes() {
        logger.info("Obteniendo todos los restaurantes");
        List<Restaurante> restaurantes = restauranteDAO.findAllRestaurantes();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/buscar")
    public List<Restaurante> buscarRestaurantes(@RequestParam(required = false) String nombre,
                                                @RequestParam(required = false) String codigoPostal) {
        if (nombre != null) {
            return restauranteDAO.findByNombreContaining(nombre);
        } else if (codigoPostal != null) {
            return restauranteDAO.findByCodigoPostal(codigoPostal);
        } else {
            return restauranteDAO.findAll();
        }
    }
    @PostMapping("/{id}/favorito")
    public ResponseEntity<Void> marcarComoFavorito(@PathVariable Long id) {
        Optional<Restaurante> restauranteOptional = restauranteDAO.findById(idUsuario);
        if (restauranteOptional.isPresent()) {
            Restaurante restaurante = restauranteOptional.get();
            restaurante.setFavorito(true);
            restauranteDAO.save(restaurante);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}