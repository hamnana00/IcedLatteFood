package icedLatteFood.presentacion;

import icedLatteFood.dominio.entidades.Restaurante;
import icedLatteFood.dominio.entidades.Direccion;
import icedLatteFood.dominio.controladores.GestorRestaurantes;

import java.util.Scanner;

public class IUAltaRestaurante {

    private GestorRestaurantes gestorRestaurantes; // Controlador de restaurantes

    public IUAltaRestaurante(GestorRestaurantes gestorRestaurantes) {
        this.gestorRestaurantes = gestorRestaurantes; // Inicializa el gestor
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("***** Alta Restaurante *****");

        // Captura de datos del restaurante
        System.out.print("Nombre del Restaurante: ");
        String nombre = scanner.nextLine();

        System.out.print("CIF del Restaurante: ");
        String cif = scanner.nextLine();

        System.out.print("¿Es favorito? (true/false): ");
        boolean favorito = scanner.nextBoolean();
        scanner.nextLine(); // Consumir la nueva línea pendiente

        // Captura de datos de la dirección
        System.out.print("Calle: ");
        String calle = scanner.nextLine();

        System.out.print("Número: ");
        int numero = scanner.nextInt(); // Se asume que el número es un entero
        scanner.nextLine(); // Consumir la nueva línea pendiente

        System.out.print("Complemento (opcional): ");
        String complemento = scanner.nextLine();

        System.out.print("Código Postal: ");
        int codigoPostal = scanner.nextInt(); // Se asume que el código postal es un entero
        scanner.nextLine(); // Consumir la nueva línea pendiente

        System.out.print("Municipio: ");
        String municipio = scanner.nextLine();

        // Creación de la dirección
        Direccion direccion = new Direccion(calle, numero, complemento, codigoPostal, municipio);

        // Registro del nuevo restaurante
        Restaurante nuevoRestaurante = gestorRestaurantes.registrarRestaurante(nombre, cif, favorito, direccion);

        // Aquí deberías insertar el restaurante en la base de datos usando el DAO
        if (gestorRestaurantes.getRestauranteDAO().insert(nuevoRestaurante) > 0) {
            System.out.println("Restaurante agregado con éxito.");
        } else {
            System.out.println("Error al agregar el restaurante.");
        }
    }
}
