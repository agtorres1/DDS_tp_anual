import {Departamento} from "./Departamento";

type Parametro = {
    campos: Array<string>
    max: number
    provincia: Array<string>
}

export class ListadoDeMunicipiosDeProvincia {
    cantidad: number
    inicio: number
    total: number
    parametros: Parametro
    provincias: Array<Departamento>

}