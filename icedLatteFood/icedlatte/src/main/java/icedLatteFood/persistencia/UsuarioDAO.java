package icedLatteFood.persistencia;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import icedLatteFood.dominio.entidades.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, String> {
    void save(Usuario usuario);
    Usuario findById(String idUsuario);
    List<Usuario> findAll();
    void update(Usuario usuario);
    void delete(String idUsuario);
}