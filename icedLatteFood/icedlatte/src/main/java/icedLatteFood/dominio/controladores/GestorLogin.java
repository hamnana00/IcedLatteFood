package icedLatteFood.dominio.controladores;

import icedLatteFood.dominio.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import icedLatteFood.servicios.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestorLogin {
/*
    @Autowired
    private DataBaseService dataBaseService;
    // Lista de usuarios simulando una base de datos
    private List<Usuario> usuarios;

    // Constructor que inicializa la lista de usuarios
    public GestorLogin() {
        usuarios = new ArrayList<>();
    }

    // Método para registrar un nuevo usuario
    public void registrarUsuario(String pass) {
        Usuario nuevoUsuario = new Usuario(pass);
        nuevoUsuario.setPass(pass);
        usuarios.add(nuevoUsuario);
    }

    // Método para iniciar sesión
    public boolean login(String id, String pass) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equals(id)) {
                return usuario.autenticar(pass);
            }
        }
        return false; // Si no se encontró el usuario o la contraseña es incorrecta
    }

    @GetMapping("/test-connection")
    public String testConnection() {
        dataBaseService.testConnection();
        return "Connection tested!";
    }
    */

}
