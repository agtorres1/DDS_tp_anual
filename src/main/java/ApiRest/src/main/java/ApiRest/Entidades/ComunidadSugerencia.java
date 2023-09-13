package ApiRest.Entidades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import java.util.HashMap;

import java.time.LocalDate;

@Setter @Getter
public class ComunidadSugerencia {
    private Comunidad comunidad1;
    private Comunidad comunidad2;

    public void setParComunidad(Comunidad primerComunidad, Comunidad segundaComunidad) throws JsonProcessingException {
        String fechaActual = LocalDate.now().toString();

        PropuestaAnterior propuestaPrimera = new PropuestaAnterior(segundaComunidad.getId(), fechaActual);
        primerComunidad.getPropuestasAnteriores().add(propuestaPrimera);

        PropuestaAnterior propuestaSegunda = new PropuestaAnterior(primerComunidad.getId(), fechaActual);
        segundaComunidad.getPropuestasAnteriores().add(propuestaSegunda);

        setComunidad1(primerComunidad);
        setComunidad2(segundaComunidad);

        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("comunidad1", primerComunidad);
        jsonResponse.put("comunidad2", segundaComunidad);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String jsonResponseString = objectMapper.writeValueAsString(jsonResponse);

    }
}
