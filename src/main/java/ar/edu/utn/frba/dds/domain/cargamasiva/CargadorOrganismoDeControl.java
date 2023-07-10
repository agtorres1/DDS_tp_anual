package ar.edu.utn.frba.dds.domain.cargamasiva;
import ar.edu.utn.frba.dds.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.domain.entidadesExtra.EntidadControladora;
import ar.edu.utn.frba.dds.domain.entidadesExtra.OrganismoDeControl;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;

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
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] dato = linea.split(",");
                String nombre = dato[0];
                String descripcion = dato[1];
                String localizacion = dato[2];

                OrganismoDeControl organismoDeControl = new OrganismoDeControl(nombre);
                organismoDeControl.setDescripcion(descripcion);
                organismoDeControl.setLocalizacion(new Localizacion(localizacion));
                organismosDeControl.add(organismoDeControl);
            }
        }catch (Exception e) {
            System.out.println("Error el leer el archivo");
            return null;
        }
        return organismosDeControl;
    }
}