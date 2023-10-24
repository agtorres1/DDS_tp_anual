package ar.edu.utn.frba.dds.models.domain.comunidades.gradosDeConfianza;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter

@Embeddable
public class Puntaje {
    @Setter
    @Column(name = "puntaje")
    private Double valor;

    @Embedded
    private GradoDeConfianza gradoDeConfianza;

    public Puntaje(Double valor){
        this.valor = valor;
        this.gradoDeConfianza = new GradoDeConfianza();
    }

    public Puntaje() {

    }


    public void actualizarPuntaje(double puntajeNuevo){

        this.valor = puntajeNuevo;
        calcularGrado(puntajeNuevo);
    }

    private void calcularGrado(double puntajeNuevo) {
        if(puntajeNuevo < 2){
            this.gradoDeConfianza.setNombre(TipoDeGrado.NO_CONFIABLE);
        } else if (puntajeNuevo>=2 && puntajeNuevo<=3){
            this.gradoDeConfianza.setNombre(TipoDeGrado.CON_RESERVAS);
        } else {
            this.gradoDeConfianza.setNombre(TipoDeGrado.CONFIABLE);
            this.gradoDeConfianza.calcularNivel(puntajeNuevo);
        }
    }




}
