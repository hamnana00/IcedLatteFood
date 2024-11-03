    package icedLatteFood.dominio.controladores;

    import icedLatteFood.dominio.entidades.Restaurante;
    import icedLatteFood.dominio.entidades.ItemMenu;
    import icedLatteFood.dominio.entidades.Direccion;
    import icedLatteFood.dominio.entidades.TipoItemMenu;
    import icedLatteFood.persistencia.RestauranteDAO;

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
        public Restaurante registrarRestaurante(String nombre, String cif, boolean favorito, Direccion direccion) {
            // Crear una nueva instancia de Restaurante con los datos proporcionados
            Restaurante nuevoRestaurante = new Restaurante(nombre, cif, favorito, direccion);
            // Aquí podrías llamar al DAO para guardar el nuevo restaurante en la base de datos
            // restauranteDAO.insert(nuevoRestaurante);
            return nuevoRestaurante;
        }

        // Funcionalidad para editar la carta del restaurante
        public void editarCarta(Restaurante restaurante, List<ItemMenu> items) {
            // Se reemplaza el menú actual del restaurante con los nuevos items
            restaurante.getCartaMenu().setItems(items);
            // También podrías actualizar el menú en la base de datos usando el DAO
            // menuDAO.update(restaurante.getMenu());
        }

        // Crear un nuevo ItemMenu y añadirlo al restaurante
        public void crearItem(Restaurante restaurante, String nombre, double precio) {
            // Validar que el restaurante no sea nulo
            if (restaurante == null) {
                throw new IllegalArgumentException("El restaurante no puede ser nulo");
            }

            // Crear una nueva instancia de ItemMenu
            ItemMenu nuevoItem = new ItemMenu(nombre, precio);

            // Añadir el nuevo ítem al menú del restaurante
            restaurante.getCartaMenu().addItem(nuevoItem);  // Asumiendo que getMenu() devuelve un objeto que tiene un método addItem

            // Si deseas, aquí puedes agregar lógica para guardar el nuevo ítem en la base de datos, si es necesario.
            // itemMenuDAO.insert(nuevoItem);
        }
    }
