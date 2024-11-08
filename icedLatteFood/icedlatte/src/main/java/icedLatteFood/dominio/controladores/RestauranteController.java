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
     public List<Restaurante> listarRestaurantes() {
        logger.info("Listando todos los restaurantes");
        return restauranteDAO.findAllRestaurantes();
    }

    @GetMapping("/buscar") //buscar restaurante
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
    /*@GetMapping("/buscarcp") //buscar restaurante por codigopostal
    public List<Restaurante> buscarRestaurantes(@RequestParam(required = false) String codigoPostal) {
        if (codigoPostal != null) {
            return restauranteDAO.findByCodigoPostal(codigoPostal);
        } else {
            return restauranteDAO.findAll();
        }
    }*/ //no es necesario separar el metodo anterior porque busca dde las dos formas

    @PostMapping("/{idUsuario}/favorito")
    public ResponseEntity<Void> marcarComoFavorito(@PathVariable String idUsuario) {
        logger.info("Marcando restaurante con ID: {} como favorito", idUsuario);
        Optional<Restaurante> restauranteOptional = restauranteDAO.findById(idUsuario);
        if (restauranteOptional.isPresent()) {
            Restaurante restaurante = restauranteOptional.get();
            restaurante.setFavorito(true);
            restauranteDAO.save(restaurante);
            logger.info("Restaurante con ID: {} marcado como favorito", idUsuario);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}