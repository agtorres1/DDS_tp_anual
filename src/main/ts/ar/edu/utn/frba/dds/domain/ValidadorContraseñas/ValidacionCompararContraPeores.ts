import * as fs from 'fs';
import { Validable } from './Validable';

class ValidacionCompararContraPeores implements Validable {
    #contraseñasComunes: Array<string>;

    constructor() {
        this.#contraseñasComunes = new Array<string>();
    }

    esValida(clave: string): boolean {
        if (this.#contraseñasComunes == null) {
            this.#leerContraseñasDesdeArchivo();
        }
        return this.#contraseñasComunes.includes(clave);
    };

    #leerContraseñasDesdeArchivo(): void {
        const file = fs.readFileSync("./10000-most-common-passwords.txt", "utf-8");
        this.#contraseñasComunes = file.split('\r\n');
    };
}