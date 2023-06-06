package ar.edu.utn.frba.dds.domain.serviciospublicos;

import java.util.Arrays;
import java.util.List;

import static ar.edu.utn.frba.dds.domain.serviciospublicos.CategoriaPosible.*;

public enum TipoEntidad implements ListadoDeCategorias{
    LINEA_TRANSPORTE{
        @Override
        public List<CategoriaPosible> generarListadoDeCategorias() {
            return Arrays.asList(FERROCARRIL,SUBTERRANEO);
        }
    },
    SUPERMERCADO{
        @Override
        public List<CategoriaPosible> generarListadoDeCategorias() {
            return Arrays.asList(SUPERMERCADO_CAT);
        }
    },
    BANCO{
        @Override
        public List<CategoriaPosible> generarListadoDeCategorias() {
            return Arrays.asList(BANCO_CAT);
        }
    }

}
