package dominio.controladores;

import dominio.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

public class GestorLogin {

    // Lista de usuarios simulando una base de datos
    private List<Usuario> usuarios;

    // Constructor que inicializa la lista de usuarios
    public GestorLogin() {
        usuarios = new ArrayList<>();
    }

    // Método para registrar un nuevo usuario
    public void registrarUsuario(String id, String pass) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setIdUsuario(id);
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
}
