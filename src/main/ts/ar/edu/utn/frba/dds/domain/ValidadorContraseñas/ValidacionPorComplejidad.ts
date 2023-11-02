import {Validable} from "./Validable";

class ValidacionPorComplejidad implements Validable {
    esValida(clave: string): boolean {
        return  RegExp("[A-Z]").test(clave) &&
                RegExp("[a-z]").test(clave) &&
                RegExp("[0-9]").test(clave);
    };
}