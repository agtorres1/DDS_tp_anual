package ApiRest.controladores;

import ApiRest.Entidades.ApiResponse;
import ApiRest.Entidades.Comunidad;
import ApiRest.Entidades.ComunidadSugerencia;
import ApiRest.Entidades.SugerenciaRequest;
import ApiRest.criterios.Criterio;
import ApiRest.criterios.CriterioCoincidencia;
import ApiRest.criterios.CriterioGradoDeConfiabilidad;
import ApiRest.criterios.CriterioPropuestaAnterior;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AnalizarFusionController implements Handler {
    List<Criterio> criterios;
    public AnalizarFusionController(){
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
            respuesta.setExito(true);
            respuesta.setCodigoDeEstado(HttpStatus.OK.getCode());
            respuesta.setResultado(sugerencias);
            context.status(HttpStatus.OK.getCode());
            context.result("Analisis realizado");
        }catch(Exception e){
            respuesta.setExito(false);
            respuesta.setError(e.getMessage());
            respuesta.setCodigoDeEstado(HttpStatus.INTERNAL_SERVER_ERROR.getCode());
            context.status(HttpStatus.INTERNAL_SERVER_ERROR.getCode());
        }

        context.json(respuesta);

    }
    private boolean cumplenCriterios(Comunidad comunidad1, Comunidad comunidad2){
        return criterios.stream().allMatch(criterio ->{
            return criterio.validarCriterio(comunidad1,comunidad2);
        });
    }


}
