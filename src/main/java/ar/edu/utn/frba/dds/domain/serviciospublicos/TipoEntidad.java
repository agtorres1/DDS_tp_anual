package ar.edu.utn.frba.dds.domain.serviciospublicos;

import java.util.Arrays;
import java.util.List;

import static ar.edu.utn.frba.dds.domain.serviciospublicos.ServicioPublicoPosible.*;

public enum TipoEntidad implements ListadoDeCategorias{
    LINEA_TRANSPORTE{
        @Override
        public List<ServicioPublicoPosible> generarListadoDeCategorias() {
            return Arrays.asList(FERROCARRIL,SUBTERRANEO);
        }
    },
    TIPO_SUPERMERCADO{
        @Override
        public List<ServicioPublicoPosible> generarListadoDeCategorias() {
            return Arrays.asList(SUPERMERCADO);
        }
    },
    TIPO_BANCO{
        @Override
        public List<ServicioPublicoPosible> generarListadoDeCategorias() {
            return Arrays.asList(BANCO);
        }
    }

}
