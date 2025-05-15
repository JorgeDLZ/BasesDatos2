package biblioteca.app;

import com.biblioteca.dao.*;
import com.biblioteca.model.*;

import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BibliotecaApp {

    private static AutorDAO autorDAO = new AutorDAO();
    private static LibroDAO libroDAO = new LibroDAO();
    private static MiembroDAO miembroDAO = new MiembroDAO();
    private static PrestamoDAO prestamoDAO = new PrestamoDAO();
    private static Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    menuAutores();
                    break;
                case 2:
                    menuLibros();
                    break;
                case 3:
                    menuMiembros();
                    break;
                case 4:
                    menuPrestamos();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- Menú Principal de la Biblioteca ---");
        System.out.println("1. Administrar Autores");
        System.out.println("2. Administrar Libros");
        System.out.println("3. Administrar Miembros");
        System.out.println("4. Administrar Préstamos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
            scanner.next(); // Limpiar la entrada inválida
            System.out.print("Seleccione una opción: ");
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        return opcion;
    }



    private static void menuAutores() {
        int opcion;
        do {
            mostrarMenuAutores();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    agregarAutor();
                    break;
                case 2:
                    listarAutores();
                    break;
                case 3:
                    actualizarAutor();
                    break;
                case 4:
                    eliminarAutor();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenuAutores() {
        System.out.println("\n--- Administrar Autores ---");
        System.out.println("1. Agregar Nuevo Autor");
        System.out.println("2. Ver Todos los Autores");
        System.out.println("3. Actualizar Autor por ID");
        System.out.println("4. Eliminar Autor por ID");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarAutor() {
        System.out.println("\n--- Agregar Nuevo Autor ---");
        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese Nacionalidad: ");
        String nacionalidad = scanner.nextLine();

        Autor nuevoAutor = new Autor(nombre, apellido, nacionalidad);
        autorDAO.addAutor(nuevoAutor);
        System.out.println("Autor agregado con éxito. ID: " + nuevoAutor.getAutorId());
    }

    private static void listarAutores() {
        System.out.println("\n--- Listado de Autores ---");
        List<Autor> autores = autorDAO.getAllAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            for (Autor autor : autores) {
                System.out.println(autor);
            }
        }
    }

    private static void actualizarAutor() {
        System.out.println("\n--- Actualizar Autor ---");
        System.out.print("Ingrese el ID del autor a actualizar: ");
        int id = leerOpcion();

        Autor autorExistente = autorDAO.getAutorById(id);
        if (autorExistente == null) {
            System.out.println("Autor con ID " + id + " no encontrado.");
            return;
        }

        System.out.println("Autor actual: " + autorExistente);

        System.out.print("Ingrese nuevo Nombre (" + autorExistente.getNombre() + "): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            autorExistente.setNombre(nombre);
        }

        System.out.print("Ingrese nuevo Apellido (" + autorExistente.getApellido() + "): ");
        String apellido = scanner.nextLine();
        if (!apellido.isEmpty()) {
            autorExistente.setApellido(apellido);
        }

        System.out.print("Ingrese nueva Nacionalidad (" + autorExistente.getNacionalidad() + "): ");
        String nacionalidad = scanner.nextLine();
        if (!nacionalidad.isEmpty()) {
            autorExistente.setNacionalidad(nacionalidad);
        }

        autorDAO.updateAutor(autorExistente);
        System.out.println("Autor actualizado con éxito.");
    }

    private static void eliminarAutor() {
        System.out.println("\n--- Eliminar Autor ---");
        System.out.print("Ingrese el ID del autor a eliminar: ");
        int id = leerOpcion();

        Autor autorExistente = autorDAO.getAutorById(id);
        if (autorExistente == null) {
            System.out.println("Autor con ID " + id + " no encontrado.");
            return;
        }

        System.out.println("¿Está seguro de eliminar el autor: " + autorExistente + "? (s/n)");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            autorDAO.deleteAutor(id);
            System.out.println("Autor eliminado con éxito.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    // --- Menu Libros ---
    private static void menuLibros() {
        int opcion;
        do {
            mostrarMenuLibros();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    agregarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    actualizarLibro();
                    break;
                case 4:
                    eliminarLibro();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenuLibros() {
        System.out.println("\n--- Administrar Libros ---");
        System.out.println("1. Agregar Nuevo Libro");
        System.out.println("2. Ver Todos los Libros");
        System.out.println("3. Actualizar Libro por ID");
        System.out.println("4. Eliminar Libro por ID");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarLibro() {
        System.out.println("\n--- Agregar Nuevo Libro ---");
        System.out.print("Ingrese Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese Género: ");
        String genero = scanner.nextLine();
        System.out.print("Ingrese ID del Autor (0 si desconocido): ");
        int autorId = leerOpcion();

        if (autorId > 0) {
            Autor autor = autorDAO.getAutorById(autorId);
            if (autor == null) {
                System.out.println("Advertencia: Autor con ID " + autorId + " no encontrado. Se agregará el libro sin autor asignado.");
                autorId = 0;
            }
        }

        Libro nuevoLibro = new Libro(titulo, genero, autorId);
        libroDAO.addLibro(nuevoLibro);
        System.out.println("Libro agregado con éxito. ID: " + nuevoLibro.getLibroId());
    }

    private static void listarLibros() {
        System.out.println("\n--- Listado de Libros ---");
        List<Libro> libros = libroDAO.getAllLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            for (Libro libro : libros) {
                System.out.print(libro);
                if (libro.getAutorId() > 0) {
                    Autor autor = autorDAO.getAutorById(libro.getAutorId());
                    if (autor != null) {
                        System.out.println(" (Autor: " + autor.getNombre() + " " + autor.getApellido() + ")");
                    } else {
                        System.out.println(" (Autor ID: " + libro.getAutorId() + " - No encontrado)");
                    }
                } else {
                    System.out.println(" (Sin Autor)");
                }
            }
        }
    }

    private static void actualizarLibro() {
        System.out.println("\n--- Actualizar Libro ---");
        System.out.print("Ingrese el ID del libro a actualizar: ");
        int id = leerOpcion();

        Libro libroExistente = libroDAO.getLibroById(id);
        if (libroExistente == null) {
            System.out.println("Libro con ID " + id + " no encontrado.");
            return;
        }

        System.out.println("Libro actual: " + libroExistente);

        System.out.print("Ingrese nuevo Título (" + libroExistente.getTitulo() + "): ");
        String titulo = scanner.nextLine();
        if (!titulo.isEmpty()) {
            libroExistente.setTitulo(titulo);
        }

        System.out.print("Ingrese nuevo Género (" + libroExistente.getGenero() + "): ");
        String genero = scanner.nextLine();
        if (!genero.isEmpty()) {
            libroExistente.setGenero(genero);
        }

        System.out.print("Ingrese nuevo ID del Autor (" + (libroExistente.getAutorId() > 0 ? libroExistente.getAutorId() : "0 - Desconocido") + "): ");
        String autorIdStr = scanner.nextLine();
        if (!autorIdStr.isEmpty()) {
            try {
                int nuevoAutorId = Integer.parseInt(autorIdStr);
                if (nuevoAutorId >= 0) { // Permitir 0 para desconocido
                    if (nuevoAutorId > 0) {
                        Autor autor = autorDAO.getAutorById(nuevoAutorId);
                        if (autor == null) {
                            System.out.println("Advertencia: Autor con ID " + nuevoAutorId + " no encontrado. Manteniendo autor anterior o asignando desconocido.");

                        } else {
                            libroExistente.setAutorId(nuevoAutorId);
                        }
                    } else {
                        libroExistente.setAutorId(0);
                    }
                } else {
                    System.out.println("ID de Autor no válido. Debe ser 0 o positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ID de Autor no válido. Manteniendo el ID anterior.");
            }
        }

        libroDAO.updateLibro(libroExistente);
        System.out.println("Libro actualizado con éxito.");
    }

    private static void eliminarLibro() {
        System.out.println("\n--- Eliminar Libro ---");
        System.out.print("Ingrese el ID del libro a eliminar: ");
        int id = leerOpcion();

        Libro libroExistente = libroDAO.getLibroById(id);
        if (libroExistente == null) {
            System.out.println("Libro con ID " + id + " no encontrado.");
            return;
        }

        System.out.println("¿Está seguro de eliminar el libro: " + libroExistente + "? (s/n)");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            libroDAO.deleteLibro(id);
            System.out.println("Libro eliminado con éxito.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    // --- Menu Miembros ---
    private static void menuMiembros() {
        int opcion;
        do {
            mostrarMenuMiembros();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    agregarMiembro();
                    break;
                case 2:
                    listarMiembros();
                    break;
                case 3:
                    actualizarMiembro();
                    break;
                case 4:
                    eliminarMiembro();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenuMiembros() {
        System.out.println("\n--- Administrar Miembros ---");
        System.out.println("1. Agregar Nuevo Miembro");
        System.out.println("2. Ver Todos los Miembros");
        System.out.println("3. Actualizar Miembro por ID");
        System.out.println("4. Eliminar Miembro por ID");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarMiembro() {
        System.out.println("\n--- Agregar Nuevo Miembro ---");
        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese Fecha de Inscripción (YYYY-MM-DD): ");
        Date fechaInscripcion = parseDate(scanner.nextLine());
        if (fechaInscripcion == null) {
            System.out.println("Fecha inválida. Operación cancelada.");
            return;
        }

        Miembro nuevoMiembro = new Miembro(nombre, apellido, fechaInscripcion);
        miembroDAO.addMiembro(nuevoMiembro);
        System.out.println("Miembro agregado con éxito. ID: " + nuevoMiembro.getMiembroId());
    }

    private static void listarMiembros() {
        System.out.println("\n--- Listado de Miembros ---");
        List<Miembro> miembros = miembroDAO.getAllMiembros();
        if (miembros.isEmpty()) {
            System.out.println("No hay miembros registrados.");
        } else {
            for (Miembro miembro : miembros) {
                System.out.println(miembro);
            }
        }
    }

    private static void actualizarMiembro() {
        System.out.println("\n--- Actualizar Miembro ---");
        System.out.print("Ingrese el ID del miembro a actualizar: ");
        int id = leerOpcion();

        Miembro miembroExistente = miembroDAO.getMiembroById(id);
        if (miembroExistente == null) {
            System.out.println("Miembro con ID " + id + " no encontrado.");
            return;
        }

        System.out.println("Miembro actual: " + miembroExistente);

        System.out.print("Ingrese nuevo Nombre (" + miembroExistente.getNombre() + "): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            miembroExistente.setNombre(nombre);
        }

        System.out.print("Ingrese nuevo Apellido (" + miembroExistente.getApellido() + "): ");
        String apellido = scanner.nextLine();
        if (!apellido.isEmpty()) {
            miembroExistente.setApellido(apellido);
        }

        System.out.print("Ingrese nueva Fecha de Inscripción (YYYY-MM-DD) (" + DATE_FORMAT.format(miembroExistente.getFechaInscripcion()) + "): ");
        String fechaStr = scanner.nextLine();
        if (!fechaStr.isEmpty()) {
            Date nuevaFecha = parseDate(fechaStr);
            if (nuevaFecha != null) {
                miembroExistente.setFechaInscripcion(nuevaFecha);
            } else {
                System.out.println("Fecha inválida. Manteniendo la fecha anterior.");
            }
        }

        miembroDAO.updateMiembro(miembroExistente);
        System.out.println("Miembro actualizado con éxito.");
    }

    private static void eliminarMiembro() {
        System.out.println("\n--- Eliminar Miembro ---");
        System.out.print("Ingrese el ID del miembro a eliminar: ");
        int id = leerOpcion();

        Miembro miembroExistente = miembroDAO.getMiembroById(id);
        if (miembroExistente == null) {
            System.out.println("Miembro con ID " + id + " no encontrado.");
            return;
        }

        System.out.println("¿Está seguro de eliminar el miembro: " + miembroExistente + "? (s/n)");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            miembroDAO.deleteMiembro(id);
            System.out.println("Miembro eliminado con éxito.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }


    // --- Menu Préstamos ---
    private static void menuPrestamos() {
        int opcion;
        do {
            mostrarMenuPrestamos();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    agregarPrestamo();
                    break;
                case 2:
                    listarPrestamos();
                    break;
                case 3:
                    actualizarPrestamo();
                    break;
                case 4:
                    eliminarPrestamo();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenuPrestamos() {
        System.out.println("\n--- Administrar Préstamos ---");
        System.out.println("1. Agregar Nuevo Préstamo");
        System.out.println("2. Ver Todos los Préstamos");
        System.out.println("3. Actualizar Préstamo por ID");
        System.out.println("4. Eliminar Préstamo por ID");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarPrestamo() {
        System.out.println("\n--- Agregar Nuevo Préstamo ---");
        System.out.print("Ingrese ID del Libro: ");
        int libroId = leerOpcion();
        Libro libro = libroDAO.getLibroById(libroId);
        if (libro == null) {
            System.out.println("Error: Libro con ID " + libroId + " no encontrado.");
            return;
        }

        System.out.print("Ingrese ID del Miembro: ");
        int miembroId = leerOpcion();
        Miembro miembro = miembroDAO.getMiembroById(miembroId);
        if (miembro == null) {
            System.out.println("Error: Miembro con ID " + miembroId + " no encontrado.");
            return;
        }

        System.out.print("Ingrese Fecha de Préstamo (YYYY-MM-DD): ");
        Date fechaPrestamo = parseDate(scanner.nextLine());
        if (fechaPrestamo == null) {
            System.out.println("Fecha de préstamo inválida. Operación cancelada.");
            return;
        }

        // La fecha de devolución puede ser nula al inicio
        Prestamo nuevoPrestamo = new Prestamo(libroId, miembroId, fechaPrestamo, null);
        prestamoDAO.addPrestamo(nuevoPrestamo);
        System.out.println("Préstamo agregado con éxito. ID: " + nuevoPrestamo.getPrestamoId());
    }

    private static void listarPrestamos() {
        System.out.println("\n--- Listado de Préstamos ---");
        List<Prestamo> prestamos = prestamoDAO.getAllPrestamos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            for (Prestamo prestamo : prestamos) {
                System.out.print(prestamo);
                Libro libro = libroDAO.getLibroById(prestamo.getLibroId());
                Miembro miembro = miembroDAO.getMiembroById(prestamo.getMiembroId());
                System.out.print(" (Libro: " + (libro != null ? libro.getTitulo() : "Desconocido") + ")");
                System.out.println(" (Miembro: " + (miembro != null ? miembro.getNombre() + " " + miembro.getApellido() : "Desconocido") + ")");
            }
        }
    }

    private static void actualizarPrestamo() {
        System.out.println("\n--- Actualizar Préstamo ---");
        System.out.print("Ingrese el ID del préstamo a actualizar: ");
        int id = leerOpcion();

        Prestamo prestamoExistente = prestamoDAO.getPrestamoById(id);
        if (prestamoExistente == null) {
            System.out.println("Préstamo con ID " + id + " no encontrado.");
            return;
        }

        System.out.println("Préstamo actual: " + prestamoExistente);

        System.out.print("Ingrese nuevo ID del Libro (" + prestamoExistente.getLibroId() + "): ");
        String libroIdStr = scanner.nextLine();
        if (!libroIdStr.isEmpty()) {
            try {
                int nuevoLibroId = Integer.parseInt(libroIdStr);
                Libro libro = libroDAO.getLibroById(nuevoLibroId);
                if (libro == null) {
                    System.out.println("Advertencia: Libro con ID " + nuevoLibroId + " no encontrado. Manteniendo el ID anterior.");
                } else {
                    prestamoExistente.setLibroId(nuevoLibroId);
                }
            } catch (NumberFormatException e) {
                System.out.println("ID de Libro no válido. Manteniendo el ID anterior.");
            }
        }

        System.out.print("Ingrese nuevo ID del Miembro (" + prestamoExistente.getMiembroId() + "): ");
        String miembroIdStr = scanner.nextLine();
        if (!miembroIdStr.isEmpty()) {
            try {
                int nuevoMiembroId = Integer.parseInt(miembroIdStr);
                Miembro miembro = miembroDAO.getMiembroById(nuevoMiembroId);
                if (miembro == null) {
                    System.out.println("Advertencia: Miembro con ID " + nuevoMiembroId + " no encontrado. Manteniendo el ID anterior.");
                } else {
                    prestamoExistente.setMiembroId(nuevoMiembroId);
                }
            } catch (NumberFormatException e) {
                System.out.println("ID de Miembro no válido. Manteniendo el ID anterior.");
            }
        }

        System.out.print("Ingrese nueva Fecha de Préstamo (YYYY-MM-DD) (" + DATE_FORMAT.format(prestamoExistente.getFechaPrestamo()) + "): ");
        String fechaPrestamoStr = scanner.nextLine();
        if (!fechaPrestamoStr.isEmpty()) {
            Date nuevaFecha = parseDate(fechaPrestamoStr);
            if (nuevaFecha != null) {
                prestamoExistente.setFechaPrestamo(nuevaFecha);
            } else {
                System.out.println("Fecha de préstamo inválida. Manteniendo la fecha anterior.");
            }
        }


        System.out.print("Ingrese nueva Fecha de Devolución (YYYY-MM-DD, dejar vacío si no devuelto) (" + (prestamoExistente.getFechaDevolucion() != null ? DATE_FORMAT.format(prestamoExistente.getFechaDevolucion()) : "Ninguna") + "): ");
        String fechaDevolucionStr = scanner.nextLine();
        if (!fechaDevolucionStr.isEmpty()) {
            Date nuevaFecha = parseDate(fechaDevolucionStr);
            if (nuevaFecha != null) {
                prestamoExistente.setFechaDevolucion(nuevaFecha);
            } else {
                System.out.println("Fecha de devolución inválida. Manteniendo la fecha anterior.");
            }
        } else {
            prestamoExistente.setFechaDevolucion(null);
        }


        prestamoDAO.updatePrestamo(prestamoExistente);
        System.out.println("Préstamo actualizado con éxito.");
    }

    private static void eliminarPrestamo() {
        System.out.println("\n--- Eliminar Préstamo ---");
        System.out.print("Ingrese el ID del préstamo a eliminar: ");
        int id = leerOpcion();

        Prestamo prestamoExistente = prestamoDAO.getPrestamoById(id);
        if (prestamoExistente == null) {
            System.out.println("Préstamo con ID " + id + " no encontrado.");
            return;
        }

        System.out.println("¿Está seguro de eliminar el préstamo: " + prestamoExistente + "? (s/n)");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            prestamoDAO.deletePrestamo(id);
            System.out.println("Préstamo eliminado con éxito.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }




    private static Date parseDate(String dateString) {
        try {
            return DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Formato de fecha inválido. Use YYYY-MM-DD.");
            return null;
        }
    }
}