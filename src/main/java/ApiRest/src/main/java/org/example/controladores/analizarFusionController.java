package ApiRest.src.main.java.org.example.controladores;

import ApiRest.src.main.java.org.example.criterios.Criterio;
import ApiRest.src.main.java.org.example.Entidades.Comunidad;
import ApiRest.src.main.java.org.example.Entidades.ComunidadSugerencia;
import ApiRest.src.main.java.org.example.Entidades.SugerenciaRequest;
import ApiRest.src.main.java.org.example.Entidades.ApiResponse;
import ApiRest.src.main.java.org.example.criterios.CriterioCoincidencia;
import ApiRest.src.main.java.org.example.criterios.CriterioGradoDeConfiabilidad;
import ApiRest.src.main.java.org.example.criterios.CriterioPropuestaAnterior;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class analizarFusionController implements Handler {
    List<Criterio> criterios;
    public analizarFusionController(){
        this.criterios = new ArrayList<>();
        criterios.add(new CriterioCoincidencia());
        criterios.add(new CriterioGradoDeConfiabilidad());
        criterios.add(new CriterioPropuestaAnterior());
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        ApiResponse respuesta = new ApiResponse();
        try{
            SugerenciaRequest request = context.bodyAsClass(SugerenciaRequest.class);
            List<Comunidad> comunidades = request.getComunidades();
            List<ComunidadSugerencia> sugerencias = new ArrayList<>();

            for(int i = 0; i < comunidades.size(); i++){
                Comunidad comunidad1 = comunidades.get(i);
                for(int j = i + 1; j < comunidades.size(); j++){
                    Comunidad comunidad2 = comunidades.get(j);

                    if(cumplenCriterios(comunidad1, comunidad2)){
                        ComunidadSugerencia sugerencia = new ComunidadSugerencia();
                        sugerencia.setParComunidad(comunidad1,comunidad2);
                        sugerencias.add(sugerencia);
                        break;  //Una sola propuesta posible para esa comunidad
                    }
                }
            }
            respuesta.setResultado(sugerencias);
        }catch(Exception e){
            respuesta.setExito(false);
            respuesta.setError(e.getMessage());
            respuesta.setCodigoDeEstado(HttpStatus.INTERNAL_SERVER_ERROR.getCode());
        }
        respuesta.setExito(true);
        respuesta.setCodigoDeEstado(HttpStatus.OK.getCode());

        context.json(respuesta);
    }
    private boolean cumplenCriterios(Comunidad comunidad1, Comunidad comunidad2){
        return criterios.stream().allMatch(criterio ->{
            return criterio.validarCriterio(comunidad1,comunidad2);
        });
    }


}
