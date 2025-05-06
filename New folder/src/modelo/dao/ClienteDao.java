package modelo.dao;

import modelo.javabeans.Cliente;
import java.util.List;

public interface ClienteDao {
    boolean altaCliente(Cliente cliente);
    Cliente buscarCliente(String cif); //
    List<Cliente> buscarTodos();
    boolean eliminarCliente(String cif);
    String exportar(String nombreFichero);
    List<Cliente> importar(String nombreFichero);
}
