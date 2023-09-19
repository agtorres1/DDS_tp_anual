package ApiRest.Entidades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.time.LocalDate;

@Setter @Getter
public class ComunidadSugerencia {
    private Comunidad comunidad1;
    private Comunidad comunidad2;

    public void setParComunidad(Comunidad primerComunidad, Comunidad segundaComunidad){
        setComunidad1(primerComunidad);
        setComunidad2(segundaComunidad);
        actualizarPropuesta(comunidad1.getPropuestasAnteriores(),comunidad2.getId());
        actualizarPropuesta(comunidad2.getPropuestasAnteriores(),comunidad1.getId());

 /*       Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("comunidad1", primerComunidad);
        jsonResponse.put("comunidad2", segundaComunidad);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String jsonResponseString = objectMapper.writeValueAsString(jsonResponse);
*/
    }

    public void actualizarPropuesta(List<PropuestaAnterior> propuestas, Long idBuscado){
        Boolean encontrado = false;

        for (PropuestaAnterior propuestaAnterior : propuestas) {
            if (propuestaAnterior.getIdComunidad() == idBuscado) {
                propuestaAnterior.setFecha(LocalDate.now().toString());
                encontrado = true;
            }
        }
        if (!encontrado) {
            propuestas.add(new PropuestaAnterior(idBuscado,LocalDate.now().toString()));
        }

    }

}
