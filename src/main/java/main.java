import ValidadorContrasenias.ValidadorDeContrasenias;

public class main {
  public static void main(String[] args) {

    System.out.println("Empezamos la ejecución del programa");
    ValidadorDeContrasenias validador = new ValidadorDeContrasenias();



    System.out.println( "Prueba clave sin mayuscula");
    System.out.println( validador.esValida("holaaaaaaaaaaaa2"));
    System.out.println( "Prueba clave sin minuscula");
    System.out.println( validador.esValida("HOLAAAAAAAAA2"));
    System.out.println( "Prueba clave de menos de 8 caracteres");
    System.out.println( validador.esValida("holA2"));
    System.out.println( "Prueba clave entre mas buscadas");
    System.out.println( validador.esValida("1Qaz2wsx"));
    System.out.println( "Prueba clave valida");
    System.out.println( validador.esValida("VamosAPromocionarCon10$"));


  }
}