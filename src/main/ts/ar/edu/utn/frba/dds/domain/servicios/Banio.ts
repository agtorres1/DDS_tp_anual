import {Genero} from "./Genero";
import {Servicio} from "./Servicio";

class Banio extends Servicio {
    #genero: Genero;
    #discapacitado: boolean;

    constructor(id: number, nombre: string, descripcion: string, genero: Genero, discapacitado: boolean) {
        super(id, nombre, descripcion);
        this.#genero = genero;
        this.#discapacitado = discapacitado;
    };

}