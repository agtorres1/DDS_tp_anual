import {TipoDeGrado} from "./TipoDeGrado";

export class GradoDeConfianza {
    nombre: TipoDeGrado;
    nivel: number;

    calcularNivel(puntajeNuevo: number) {
        this.nivel = (puntajeNuevo <= 5) ? 1 : 2;
    }
}