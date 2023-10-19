package ar.edu.utn.frba.dds.models.domain.comunidades.gradosDeConfianza;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Setter
@Getter
@Embeddable
public class GradoDeConfianza {

    @Column(name = "Tipo_de_grado")
    private TipoDeGrado nombre;

    @Column(name = "Nivel_del_grado")
    private Integer nivel;

    public void calcularNivel(double puntajeNuevo){
        if(puntajeNuevo<=5){
            this.setNivel(1);
        }
        else{
            this.setNivel(2);
        }
    }
}
