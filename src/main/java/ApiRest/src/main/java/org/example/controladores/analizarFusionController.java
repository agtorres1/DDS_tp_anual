package ApiRest.src.main.java.org.example.controladores;

import ApiRest.src.main.java.org.example.criterios.Criterio;
import ApiRest.src.main.java.org.example.Entidades.Comunidad;
import ApiRest.src.main.java.org.example.Entidades.ComunidadSugerencia;
import ApiRest.src.main.java.org.example.Entidades.SugerenciaRequest;
import ApiRest.src.main.java.org.example.Entidades.ApiResponse;
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

    private static final double MIN_COINCIDENCIA_ESTABLECIMIENTO = 0.75;
    private static final double MIN_COINCIDENCIA_SERVICIOS = 0.75;
    private static final double MIN_COINCIDENCIA_USUARIOS = 0.05;
    private static final int MIN_DIFERENCIA_PROPUESTA_MESES = 6;

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
        return calcularPorcentajeCoincidencia(comunidad1.getEstablecimientos(), comunidad2.getEstablecimientos())
                >= MIN_COINCIDENCIA_ESTABLECIMIENTO
                && calcularPorcentajeCoincidencia(comunidad1.getServicios(), comunidad2.getServicios())
                >= MIN_COINCIDENCIA_SERVICIOS
                && calcularPorcentajeCoincidencia(comunidad1.getUsuarios(), comunidad2.getUsuarios())
                >= MIN_COINCIDENCIA_USUARIOS
                && comunidad1.getGradoConfianza() == comunidad2.getGradoConfianza()
                && noFueronPropuestaAnterior(comunidad1.getPropuestasAnteriores(),comunidad2);
    }

    private Boolean noFueronPropuestaAnterior(Map<String, LocalDate> propuestasAnteriores, Comunidad posiblePropuesta){
        return propuestasAnteriores.containsKey(posiblePropuesta.getId())?
                fechasDistanciaMeses(propuestasAnteriores.get(posiblePropuesta),LocalDate.now())>=MIN_DIFERENCIA_PROPUESTA_MESES:false;
    }

    private Integer fechasDistanciaMeses(LocalDate fecha1, LocalDate fecha2) {
        return Period.between(fecha1,fecha2).getMonths();
    }

    private double calcularPorcentajeCoincidencia(List<String> lista1, List<String> lista2){
        double coincidencias = lista1.stream()
                .filter(lista2::contains)
                .count();
        return coincidencias / Math.max(lista1.size(), lista2.size());
    }
}
