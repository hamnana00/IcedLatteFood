package persistencia;

import java.sql.ResultSet;

// Clase genérica EntityDAO
public abstract class EntityDAO<E> {

    // Instancia del gestor de base de datos
    protected GestorBaseDatos gestorBD;

    // Constructor que recibe el gestor de base de datos
    public EntityDAO(GestorBaseDatos gestorBD) {
        this.gestorBD = gestorBD;
    }

    public abstract int insert(E entity);

    public abstract int update(E entity);

    public abstract int delete(E entity);

    public abstract E select(String id);
}