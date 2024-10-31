    package dominio.controladores;

    import dominio.entidades.Restaurante;
    import dominio.entidades.ItemMenu;
    import dominio.entidades.Direccion;
    import dominio.entidades.TipoItemMenu;
    import persistencia.RestauranteDAO;

    import java.util.List;

    public class GestorRestaurantes {

        private RestauranteDAO restauranteDAO;

        public GestorRestaurantes (RestauranteDAO restauranteDAO) {
            this.restauranteDAO = restauranteDAO;
        }

        //mostrar retaurantes
        public List<Restaurante> obtenerTodosRestaurantes() {
            return restauranteDAO.selectAll();  // MPLEMENTAR EN EL DAO! no olvidar
        }

        //buscar restaurantee por codigo postal o texto
        public List<Restaurante> buscarRestaurantes(String codigoPostal, String texto) {
            if (codigoPostal != null && !codigoPostal.isEmpty() && texto != null && !texto.isEmpty()) {
                return restauranteDAO.selectPorCodigoPostalYTextoLibre(codigoPostal, texto);
            } else if (codigoPostal != null && !codigoPostal.isEmpty()) {
                return restauranteDAO.selectPorCodigoPostal(codigoPostal);
            } else {
                return restauranteDAO.selectAll();
            }
        }
        //seleccionar todos son filtro
        public List<Restaurante> obtenerRestaurantes() {
            return restauranteDAO.selectAll();
        }

        // Registrar un nuevo restaurante
        public Restaurante registrarRestaurante(String nombre, String cif, Direccion direccion) {
            // Crear una nueva instancia de Restaurante con los datos proporcionados
            Restaurante nuevoRestaurante = new Restaurante(nombre, cif, direccion);
            // Aquí podrías llamar al DAO para guardar el nuevo restaurante en la base de datos
            // restauranteDAO.insert(nuevoRestaurante);
            return nuevoRestaurante;
        }

        // Funcionalidad para editar la carta del restaurante
        public void editarCarta(Restaurante restaurante, List<ItemMenu> items) {
            // Se reemplaza el menú actual del restaurante con los nuevos items
            restaurante.getMenu().setItems(items);
            // También podrías actualizar el menú en la base de datos usando el DAO
            // menuDAO.update(restaurante.getMenu());
        }

        // Funcionalidad para crear un nuevo item del menú
        public ItemMenu crearItem(String nombre, double precio, TipoItemMenu tipo) {
            // Crear un nuevo objeto de tipo ItemMenu
            return new ItemMenu(nombre, precio, tipo);
        }
    }
