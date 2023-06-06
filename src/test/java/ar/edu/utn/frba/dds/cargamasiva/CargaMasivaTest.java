package ar.edu.utn.frba.dds.cargamasiva;
import ar.edu.utn.frba.dds.domain.cargamasiva.CargadorEntidades;
import ar.edu.utn.frba.dds.domain.comunidades.Miembro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.io.File;

public class CargaMasivaTest {

    @Test
    @DisplayName("Lectura exitosa de tres miembros a partir del archivo csv")
    public void lecturaDeTresMiembros(){
        String rutaCSV = "src\\test\\resources\\testcsvcorrecto.txt";
        File archivoCSV = new File(rutaCSV);
        List<Miembro> nuevosMiembros = CargadorEntidades.obtenerEntidadesDesdeCSV(archivoCSV);

        Assertions.assertFalse(nuevosMiembros == null);
        Assertions.assertEquals(3,nuevosMiembros.size());
    }

    @Test
    @DisplayName("Lectura de csv vacio retorna lista vacia")
    public void lecturaDeCsvVacio(){
        String rutaCSV = "src\\test\\resources\\testcsvvacio.txt";
        File archivoCSV = new File(rutaCSV);
        List<Miembro> nuevosMiembros = CargadorEntidades.obtenerEntidadesDesdeCSV(archivoCSV);
        Assertions.assertEquals(0,nuevosMiembros.size());
    }

    @Test
    @DisplayName("Lectura de csv inexistente retorna un null")
    public void lecturaDeCsvInexistente(){
        String rutaCSV = "src\\test\\resources\\testcsvinexistente.txt";
        File archivoCSV = new File(rutaCSV);
        List<Miembro> nuevosMiembros = CargadorEntidades.obtenerEntidadesDesdeCSV(archivoCSV);
        Assertions.assertEquals(null,nuevosMiembros);
    }
}
