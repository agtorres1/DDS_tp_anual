import {BaseService, ServiceBuilder } from "ts-retrofit"

export class ServicioGeoref extends BaseService {
    static _instancia: ServicioGeoref | null = null

    static #urlAPI: string = "https://apis.datos.gob.ar/georef/api/"

    retroFit: ServiceBuilder

    /*constructor() {
        this.retroFit = new ServiceBuilder().setEndpoint(ServicioGeoref.#urlAPI).build(ServicioGeoref)
    } AYUDAAAAAA */

    /*static get instancia(): ServicioGeoref {
        return (this._instancia === null) ? new ServicioGeoref() : this._instancia
    }*/

}