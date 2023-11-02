import {GradoDeConfianza} from "./GradoDeConfianza";
import {TipoDeGrado} from "./TipoDeGrado";

export class Puntaje {
    _valor: number;
    gradoDeConfianza: GradoDeConfianza;

    constructor () {
        this.gradoDeConfianza = new GradoDeConfianza();
    }


    get valor () : number {
        return this._valor;
    }

    set valor (puntajeNuevo) {
        this._valor = puntajeNuevo;
        this.calcularGrado (puntajeNuevo);
    }

    calcularGrado (puntaje: number) {
        if (puntaje < 2) {
            this.gradoDeConfianza.nombre = TipoDeGrado.NO_CONFIABLE;
        }
        else if (3 >= puntaje && puntaje >= 2) {
            this.gradoDeConfianza.nombre = TipoDeGrado.CON_RESERVAS;
        }
        else {
            this.gradoDeConfianza.nombre = TipoDeGrado.CONFIABLE;
            this.gradoDeConfianza.calcularNivel(puntaje);
        }
    }
}