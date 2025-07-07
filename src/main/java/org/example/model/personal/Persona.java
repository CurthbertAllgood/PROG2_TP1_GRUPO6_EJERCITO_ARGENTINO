package org.example.model.personal;

public abstract class Persona {
    protected static int codigoSecuencial = 0; // Controla el siguiente código disponible
    protected int codigo; // Código individual de la persona
    protected String nombre;
    protected String apellidos;

    public Persona(int codigo, String nombre, String apellidos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public abstract String getTipo();

    public abstract String getGrado();

    // Inicializa la secuencia para evitar colisiones al agregar nuevas personas
    public static void inicializarLegajoDesde(int maxLegajo) {
        Persona.codigoSecuencial = maxLegajo;
    }

    // Genera un nuevo legajo para altas nuevas
    public static int generarNuevoLegajo() {
        return ++codigoSecuencial;
    }
}
