package ar.edu.utn.frba.dds.excepciones;

public class SinTipoExcepcion extends RuntimeException{
    public SinTipoExcepcion(){
        super("No hay configurado un tipo para el objeto a construir");
    }
}
