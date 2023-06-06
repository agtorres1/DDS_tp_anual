package ar.edu.utn.frba.dds.domain.cargamasiva;
import ar.edu.utn.frba.dds.domain.comunidades.Miembro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CargadorEntidades {
    public static List<Miembro> obtenerEntidadesDesdeCSV(File archivo){
        List nuevosMiembros = new ArrayList<Miembro>();
        String linea;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] dato = linea.split(",");
                String usuario = dato[0];
                String clave = dato[1];

                Miembro nuevoMiembro = new Miembro(usuario, clave);
                nuevosMiembros.add(nuevoMiembro);
            }
        }catch (Exception e) {
            System.out.println("Error el leer el archivo");
            return null;
        }
        return nuevosMiembros;
    }
}
