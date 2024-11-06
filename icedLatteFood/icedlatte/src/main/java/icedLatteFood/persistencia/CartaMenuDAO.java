package icedLatteFood.persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import icedLatteFood.dominio.entidades.CartaMenu;

@Repository
public interface CartaMenuDAO extends JpaRepository <CartaMenu, Long> {
    @Override
    CartaMenu save(CartaMenu cartaMenu);
    @Override
    Optional<CartaMenu> findById(Long id);
    List<CartaMenu> findAll();
    void update(CartaMenu cartaMenu);
    void delete(Long id);

    //Añadir getRestaurante
}