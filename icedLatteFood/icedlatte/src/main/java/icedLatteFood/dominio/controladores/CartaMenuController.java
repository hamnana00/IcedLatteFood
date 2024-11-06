package icedLatteFood.dominio.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import icedLatteFood.persistencia.CartaMenuDAO;
import icedLatteFood.dominio.entidades.CartaMenu;

@Controller
public class CartaMenuController {
    private static final Logger logger = LoggerFactory.getLogger(CartaMenuController.class);

    @Autowired
    private CartaMenuDAO cartaMenuDAO;

    //@PostMapping
    /*public ResponseEntity<CartaMenu> createCartaMenu(@RequestBody CartaMenu cartaMenu) {
        logger.info("Creando nueva carta de menú para el restaurante: {}", cartaMenu.getRestaurante().getNombreRestaurante());
        cartaMenuDAO.save(cartaMenu);
        return ResponseEntity.ok(cartaMenu);
    }*/

    /*@GetMapping("/{id}")
    public ResponseEntity<CartaMenu> getCartaMenu(@PathVariable Long idCarta) {
        logger.info("Obteniendo carta de menú con ID: {}", idCarta);
        CartaMenu cartaMenu = cartaMenuDAO.findById(idCarta);
        return ResponseEntity.ok(cartaMenu);
    }*/

    // Otros métodos (update, delete, etc.)

}