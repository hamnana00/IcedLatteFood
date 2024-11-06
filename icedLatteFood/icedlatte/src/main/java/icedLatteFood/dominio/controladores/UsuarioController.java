package icedLatteFood.dominio.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import icedLatteFood.*;
import icedLatteFood.persistencia.UsuarioDAO;
import icedLatteFood.dominio.entidades.Usuario;


@Controller
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioDAO usuarioDAO;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario>  registrar(@RequestBody Usuario usuario) {
        usuarioDAO.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{idUsario}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String idUsuario) {
        Usuario usuario = usuarioDAO.findById(idUsuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{Favorito}")
    public void favorito(icedLatteFood.dominio.entidades.Cliente cliente, icedLatteFood.dominio.entidades.Restaurante restaurante) {
        if (cliente != null && restaurante != null) {
            cliente.anadirFavorito(restaurante);  // Añade el restaurante a los favoritos del cliente
        } else {
            throw new IllegalArgumentException("El cliente o el restaurante no pueden ser nulos");
        }
    }
}

