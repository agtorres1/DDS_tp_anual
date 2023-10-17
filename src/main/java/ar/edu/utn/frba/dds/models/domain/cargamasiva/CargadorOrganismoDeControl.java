package ar.edu.utn.frba.dds.models.domain.cargamasiva;
import ar.edu.utn.frba.dds.models.domain.entidadesExtra.OrganismoDeControl;
import ar.edu.utn.frba.dds.models.domain.incidentes.localizaciones.Localizacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CargadorOrganismoDeControl {
    public static List<OrganismoDeControl> obtenerOrganismosDeControl(File archivo){
        List organismosDeControl = new ArrayList<OrganismoDeControl>();
        String linea;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((linea = br.readLine()) != null) {
                String[] dato = linea.split(",");
                String nombre = dato[0];
                String descripcion = dato[1];
                String provincia = dato[2];
                Localizacion localizacion = new Localizacion();
                localizacion.setProvincia(provincia);

                OrganismoDeControl organismoDeControl = new OrganismoDeControl();
                organismoDeControl.setNombre(nombre);
                organismoDeControl.setDescripcion(descripcion);
                organismoDeControl.setLocalizacion(localizacion);

                organismosDeControl.add(organismoDeControl);
            }
        }catch (Exception e) {
            System.out.println("Error el leer el archivo");
            return null;
        }
        return organismosDeControl;
    }
}