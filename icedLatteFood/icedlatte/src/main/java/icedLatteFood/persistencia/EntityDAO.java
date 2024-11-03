package icedLatteFood.persistencia;

import icedLatteFood.dominio.entidades.Restaurante;

import java.util.List;

// Clase genérica EntityDAO
public abstract class EntityDAO<E> {

    // Instancia del gestor de base de datos
    protected icedLatteFood.persistencia.GestorBaseDatos gestorBD;

    // Constructor que recibe el gestor de base de datos
    public EntityDAO(icedLatteFood.persistencia.GestorBaseDatos gestorBD) {
        this.gestorBD = gestorBD;
    }

    public abstract int insert(E entity);

    public abstract int update(E entity);

    public abstract int delete(E entity);

    public abstract E select(String id);

    public abstract List<Restaurante> selectPorCodigoPostal(String s);


}