import { Validable } from './Validable';

class ValidacionPorLongitud implements Validable {
    esValida(clave: string): boolean {
        return clave.length > 8;
    };
}
