package ar.edu.utn.frba.dds.excepciones;

public class SinUsuarioExcepcion extends RuntimeException{
   public SinUsuarioExcepcion(){
       super("No se configuró un usuario al miembro");
   }
}
