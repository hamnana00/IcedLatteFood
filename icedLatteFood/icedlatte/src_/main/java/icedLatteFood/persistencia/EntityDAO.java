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

    // Método para insertar una entidad
    public abstract int insert(E entity);

    // Método para actualizar una entidad
    public abstract int update(E entity);

    // Método para eliminar una entidad
    public abstract int delete(E entity);

    // Método para seleccionar una entidad por ID
    public abstract E select(String id);
}