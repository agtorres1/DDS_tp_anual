package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.serviciospublicos.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EntidadTest {
    private Serviciopublico serviciopublico;
    private Entidad entidad;
    private List<Establecimiento> establecimientos;
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

        this.establecimientos = new ArrayList<>();

        this.serviciopublico = new Serviciopublico();

    }

    @Test
    @DisplayName("Instanciar una linea sin agregar origen y destino a la lista de estaciones")
    public void instanciarLineaSinAgregarEnLista(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                this.entidad = new Entidad(this.establecimientos,this.origen,this.destino,TipoEntidad.TRANSPORTE));
    }

    @Test
    @DisplayName("Instanciar una linea agregando origen y destino a la lista de estaciones")
    public void instanciarLineaAgregandoEnLista(){
        Assertions.assertDoesNotThrow(()->{
            this.establecimientos.add(origen);
            this.establecimientos.add(destino);
            this.entidad = new Entidad(this.establecimientos,this.origen,this.destino,TipoEntidad.TRANSPORTE);
        });
    }
}
