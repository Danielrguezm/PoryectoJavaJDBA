package principales;

import modelo.dao.ClienteDao;
import modelo.dao.ClienteDaoImplMy8Jdbc;
import modelo.javabeans.Cliente;

import java.util.List;
import java.util.Scanner;

public class GestionClientes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClienteDao dao = new ClienteDaoImplMy8Jdbc();
        int opcion;

        do {
            System.out.println("\n--- MENÚ GESTIÓN CLIENTES ---");
            System.out.println("1. Alta del Cliente");
            System.out.println("2. Buscar un Cliente");
            System.out.println("3. Mostrar Todos");
            System.out.println("4. Eliminar un cliente");
            System.out.println("5. Exportar a fichero");
            System.out.println("6. Importar desde fichero");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.print("CIF: ");
                    String cif = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidos = sc.nextLine();
                    System.out.print("Domicilio: ");
                    String domicilio = sc.nextLine();
                    System.out.print("Facturación anual: ");
                    double facturacion = Double.parseDouble(sc.nextLine());
                    System.out.print("Número de empleados: ");
                    int empleados = Integer.parseInt(sc.nextLine());

                    Cliente nuevo = new Cliente(cif, nombre, apellidos, domicilio, facturacion, empleados);
                    boolean insertado = dao.altaCliente(nuevo);
                    System.out.println(insertado ? "Cliente dado de alta." : "Error al dar de alta.");
                }

                case 2 -> {
                    System.out.print("CIF del cliente a buscar: ");
                    String cif = sc.nextLine();
                    Cliente c = dao.buscarCliente(cif);
                    System.out.println(c != null ? c : "Cliente no encontrado.");
                }

                case 3 -> {
                    List<Cliente> lista = dao.buscarTodos();
                    if (lista.isEmpty()) {
                        System.out.println("No hay clientes en la base de datos.");
                    } else {
                        for (Cliente c : lista) {
                            System.out.println(c);
                        }
                    }
                }

                case 4 -> {
                    System.out.print("CIF del cliente a eliminar: ");
                    String cif = sc.nextLine();
                    boolean eliminado = dao.eliminarCliente(cif);
                    System.out.println(eliminado ? "Cliente eliminado." : "No se pudo eliminar.");
                }

                case 5 -> {
                    System.out.print("Nombre del fichero para exportar (ej: clientes.txt): ");
                    String fichero = sc.nextLine();
                    String resultado = dao.exportar(fichero);
                    System.out.println(resultado);
                }

                case 6 -> {
                    System.out.print("Nombre del fichero para importar (ej: clientes.txt): ");
                    String fichero = sc.nextLine();
                    List<Cliente> importados = dao.importar(fichero);
                    if (importados.isEmpty()) {
                        System.out.println("No se importo ningún cliente.");
                    } else {
                        System.out.println("Clientes importados desde el fichero:");
                        for (Cliente c : importados) {
                            System.out.println(c);
                        }
                    }
                }

                case 7 -> {
                    System.out.println("Saliendo del programa...");
                }

                default -> {
                    System.out.println("Opción no válida. Intenta de nuevo.");
                }
            }

        } while (opcion != 7);

        sc.close();
    }
}
