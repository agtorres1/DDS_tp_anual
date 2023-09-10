package ApiRest.src.main.java.org.example.Entidades;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ApiResponse<T> {
    private int codigoDeEstado;
    private String error;
    private boolean exito;
    private T resultado;
}
