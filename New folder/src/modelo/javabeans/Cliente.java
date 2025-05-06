package modelo.javabeans;

public class Cliente {

    private String cif;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private double facturacionAnual;
    private int numeroEmpleados;

    public Cliente() {}

    public Cliente(String cif, String nombre, String apellidos, String domicilio, double facturacionAnual, int numeroEmpleados) {
        this.cif = cif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.facturacionAnual = facturacionAnual;
        this.numeroEmpleados = numeroEmpleados;
    }

    public String getCif() { return cif; }
    public void setCif(String cif) { this.cif = cif; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }

    public double getFacturacionAnual() { return facturacionAnual; }
    public void setFacturacionAnual(double facturacionAnual) { this.facturacionAnual = facturacionAnual; }

    public int getNumeroEmpleados() { return numeroEmpleados; }
    public void setNumeroEmpleados(int numeroEmpleados) { this.numeroEmpleados = numeroEmpleados; }

    @Override
    public String toString() {
        return cif + " - " + nombre + " " + apellidos + ", " + domicilio +
                " | Facturación: " + facturacionAnual +
                " | Empleados: " + numeroEmpleados;
    }
}
