package ar.edu.utn.frba.dds.models.domain.cargamasiva;
import ar.edu.utn.frba.dds.models.domain.entidadesExtra.EntidadControladora;
import ar.edu.utn.frba.dds.models.domain.incidentes.localizaciones.Localizacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CargadorEntidadesControladoras {
    public static List<EntidadControladora> obtenerEntidadesControladoras(File archivo){
        List entidadesDeControl = new ArrayList<EntidadControladora>();
        String linea;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] dato = linea.split(",");
                String nombre = dato[0];
                String descripcion = dato[1];
                String direccion = dato[2];
                String provincia = dato[3];
                Localizacion localizacion = new Localizacion();
                localizacion.setProvincia(provincia);

                EntidadControladora entidad1 = new EntidadControladora();
                entidad1.setNombre(nombre);


                EntidadControladora entidadControladora = entidad1;
                entidadControladora.setDescripcion(descripcion);
                entidadControladora.setDireccion(direccion);
                entidadControladora.setLocalizacion(localizacion);
                entidadesDeControl.add(entidadControladora);
            }
        }catch (Exception e) {
            System.out.println("Error al leer el archivo");
            return null;
        }
        return entidadesDeControl;
    }
}
