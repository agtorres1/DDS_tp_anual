package ar.edu.utn.frba.dds.domain.excepciones;

public class NoEsAdministradorExcepcion extends Exception{
    public NoEsAdministradorExcepcion() {
        super("El usuario no pertenece a la lista de administradores :c.");
    }
}