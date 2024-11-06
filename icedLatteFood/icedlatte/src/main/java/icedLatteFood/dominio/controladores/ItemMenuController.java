package icedLatteFood.dominio.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import icedLatteFood.persistencia.ItemMenuDAO;
import icedLatteFood.dominio.entidades.ItemMenu;

@Controller
public class ItemMenuController {
    private static final Logger logger = LoggerFactory.getLogger(ItemMenuController.class);

    @Autowired
    private ItemMenuDAO itemMenuDAO;

    @PostMapping
    public ResponseEntity<ItemMenu> createItemMenu(@RequestBody ItemMenu itemMenu) {
        logger.info("Creando nuevo ítem de menú: {}", itemMenu.getNombre());
        itemMenuDAO.save(itemMenu);
        return ResponseEntity.ok(itemMenu);
    }

   /* @GetMapping("/{idItem}")
    public ResponseEntity<ItemMenu> getItemMenu(@PathVariable Long idItem) {
        logger.info("Obteniendo ítem de menú con ID: {}", idItem);
        ItemMenu itemMenu = itemMenuDAO.findById(idItem);
        return ResponseEntity.ok(itemMenu);
    }*/

    // Otros métodos (update, delete, etc.)

}