package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Entidad;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EntidadTest {
    private Entidad entidad;
    private List<Establecimiento> estaciones;
    private Establecimiento origen;
    private Establecimiento destino;
    private Ubicacion ubicacion1;
    private Ubicacion ubicacion2;
    @BeforeEach
    public void init(){
        this.ubicacion1 = new Ubicacion();
        this.ubicacion1.setLatitud(1.00);
        this.ubicacion1.setLongitud(-1.00);

        this.origen = new Establecimiento();
        this.origen.setNombre("Flores");
        this.origen.setCentroide(ubicacion1);

        this.ubicacion2 = new Ubicacion();
        this.ubicacion2.setLatitud(2.00);
        this.ubicacion2.setLongitud(-8.00);

        this.destino = new Establecimiento();
        this.destino.setNombre("Once");
        this.destino.setCentroide(ubicacion2);

        this.estaciones = new ArrayList<>();
    }

    @Test
    @DisplayName("Instanciar una linea sin agregar origen y destino a la lista de estaciones")
    public void instanciarLineaSinAgregarEnLista(){
        this.entidad = new Entidad(this.estaciones,this.origen,this.destino);
    }

    @Test
    @DisplayName("Instanciar una linea agregando origen y destino a la lista de estaciones")
    public void instanciarLineaAgregandoEnLista(){
        this.estaciones.add(origen);
        this.estaciones.add(destino);
        this.entidad = new Entidad(this.estaciones,this.origen,this.destino);
    }
}
