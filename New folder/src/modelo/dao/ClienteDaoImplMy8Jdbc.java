package modelo.dao;

import modelo.javabeans.Cliente;
import util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ClienteDaoImplMy8Jdbc implements ClienteDao {

    private Connection con;

    public ClienteDaoImplMy8Jdbc() {
        con = Conexion.getConexion();
    }

    @Override
    public boolean altaCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (cif, nombre, apellidos, domicilio, facturacion_anual, numero_empleados) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getCif());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDomicilio());
            ps.setDouble(5, cliente.getFacturacionAnual());
            ps.setInt(6, cliente.getNumeroEmpleados());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error altaCliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Cliente buscarCliente(String cif) {
        String sql = "SELECT * FROM clientes WHERE cif = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cif);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getString("cif"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("domicilio"),
                        rs.getDouble("facturacion_anual"),
                        rs.getInt("numero_empleados")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error buscarCliente: " + e.getMessage());
        }
        return null;
    }


    @Override
    public List<Cliente> buscarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getString("cif"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("domicilio"),
                        rs.getDouble("facturacion_anual"),
                        rs.getInt("numero_empleados")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error buscarTodos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean eliminarCliente(String cif) {
        String sql = "DELETE FROM clientes WHERE cif = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cif);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error eliminarCliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String exportar(String nombreFichero) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreFichero))) {
            for (Cliente c : buscarTodos()) {
                pw.println(c.getCif() + "," + c.getNombre() + "," + c.getApellidos() + "," +
                        c.getDomicilio() + "," + c.getFacturacionAnual() + "," + c.getNumeroEmpleados());
            }
            return "Clientes bien exportados";
        } catch (IOException e) {
            return "Fichero no existe";
        }
    }

    @Override
    public List<Cliente> importar(String nombreFichero) {
        List<Cliente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 6) {
                    Cliente c = new Cliente(
                            datos[0], // cif
                            datos[1], // nombre
                            datos[2], // apellidos
                            datos[3], // domicilio
                            Double.parseDouble(datos[4]), // facturación
                            Integer.parseInt(datos[5]) // empleados
                    );
                    lista.add(c);
                    altaCliente(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al importar: " + e.getMessage());
        }
        return lista;
    }
}
