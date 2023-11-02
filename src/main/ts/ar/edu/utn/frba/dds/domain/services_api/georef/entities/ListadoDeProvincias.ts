import {Provincia} from "./Provincia";

type Parametro = {
    campos: Array<string>
}

export class ListadoDeProvincias {
    cantidad: number
    inicio: number
    total: number
    parametros: Parametro
    provincias: Array<Provincia>

}