package icedLatteFood.persistencia;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import icedLatteFood.dominio.entidades.CartaMenu;

@Repository
public interface CartaMenuDAO extends JpaRepository <CartaMenu, Long> {
    void save(CartaMenu cartaMenu);
    CartaMenu findById(Long id);
    List<CartaMenu> findAll();
    void update(CartaMenu cartaMenu);
    void delete(Long id);
}