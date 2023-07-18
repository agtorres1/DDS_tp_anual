import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.Email;
import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.Notificacion;
import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.RangoHorario;
import ar.edu.utn.frba.dds.domain.MediosDeComunicacion.Whatsapp;
import ar.edu.utn.frba.dds.domain.ValidadorContrasenias.ValidadorDeContrasenias;
import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class main {
  public static void main(String[] args) {

    System.out.println("Empezamos la ejecución del programa");
  /*  ValidadorDeContrasenias validador = new ValidadorDeContrasenias();



    System.out.println( "Prueba clave sin mayuscula");
    System.out.println( validador.esValida("holaaaaaaaaaaaa2"));

    System.out.println( "Prueba clave sin minuscula");
    System.out.println( validador.esValida("HOLAAAAAAAAA2"));

    System.out.println( "Prueba clave de menos de 8 caracteres");
    System.out.println( validador.esValida("holA2"));

    System.out.println( "Prueba clave entre mas buscadas");
    System.out.println( validador.esValida("1Qaz2wsx"));

    System.out.println( "Prueba clave valida");
    System.out.println( validador.esValida("VamosAPromocionarCon10$"));*/

   /* Whatsapp whatsapp = new Whatsapp();
    Email email = new Email();
    whatsapp.enviarNotificacion("German NEPE");
    email.enviarNotificacion("German NEPE");*/
    /*LocalTime horaActual1 = LocalTime.of(20, 0);
    LocalTime horaActual2 = LocalTime.of(2, 0);*/

    // Caso 1: Hora de inicio: 2 y Hora actual: 20
  /*  LocalTime horaInicio1 = LocalTime.of(2, 0);
    long diferencia1 = 24 - horaInicio1.until(horaActual1, ChronoUnit.HOURS);

    // Caso 2: Hora de inicio: 20 y Hora actual: 2
    LocalTime horaInicio2 = LocalTime.of(20, 0);
    long diferencia2 =  horaActual2.until(horaInicio2, ChronoUnit.HOURS);

    System.out.println("Diferencia 1: " + diferencia1 + " horas");
    System.out.println("Diferencia 2: " + diferencia2 + " horas");*/

// Ejemplo 1: Notificacion con observaciones sobre un incidente
    Notificacion notificacion1 = new Notificacion("Problema de suministro eléctrico");
    System.out.println(notificacion1);

    // Ejemplo 2: Notificacion con observaciones sobre otro incidente
    Notificacion notificacion2 = new Notificacion("Fuga de agua en el baño");
    System.out.println(notificacion2);

    // Ejemplo 3: Notificacion con observaciones sobre un evento
    Notificacion notificacion3 = new Notificacion("Recordatorio de reunión a las 15:00");
    System.out.println(notificacion3);

    Whatsapp whatsapp = new Whatsapp();
  //  Email email = new Email();
    // Ejemplo 1: Crear un rango horario de 8:00 a 12:30
    LocalTime horaInicio1 = LocalTime.of(3, 20);
    LocalTime horaFin1 = LocalTime.of(12, 30);
    RangoHorario rangoHorario1 = new RangoHorario(horaInicio1, horaFin1);

    // Ejemplo 2: Crear otro rango horario de 13:30 a 18:00
    LocalTime horaInicio2 = LocalTime.of(13, 30);
    LocalTime horaFin2 = LocalTime.of(18, 0);
    RangoHorario rangoHorario2 = new RangoHorario(horaInicio2, horaFin2);

    // Almacenar los rangos horarios en la lista rangosHorariosElegidos
    List<RangoHorario> rangosHorariosElegidos = new ArrayList<>();
    rangosHorariosElegidos.add(rangoHorario1);
    rangosHorariosElegidos.add(rangoHorario2);

    whatsapp.setRangosHorariosElegidos(rangosHorariosElegidos);
    whatsapp.evaluarEnvioDeNotificacion(notificacion2);
  //  email.enviarNotificacion("German NEPE");
  }
}