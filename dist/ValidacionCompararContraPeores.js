var __classPrivateFieldGet = (this && this.__classPrivateFieldGet) || function (receiver, state, kind, f) {
    if (kind === "a" && !f) throw new TypeError("Private accessor was defined without a getter");
    if (typeof state === "function" ? receiver !== state || !f : !state.has(receiver)) throw new TypeError("Cannot read private member from an object whose class did not declare it");
    return kind === "m" ? f : kind === "a" ? f.call(receiver) : f ? f.value : state.get(receiver);
};
var __classPrivateFieldSet = (this && this.__classPrivateFieldSet) || function (receiver, state, value, kind, f) {
    if (kind === "m") throw new TypeError("Private method is not writable");
    if (kind === "a" && !f) throw new TypeError("Private accessor was defined without a setter");
    if (typeof state === "function" ? receiver !== state || !f : !state.has(receiver)) throw new TypeError("Cannot write private member to an object whose class did not declare it");
    return (kind === "a" ? f.call(receiver, value) : f ? f.value = value : state.set(receiver, value)), value;
};
var _ValidacionCompararContraPeores_instances, _ValidacionCompararContraPeores_contraseñasComunes, _ValidacionCompararContraPeores_leerContraseñasDesdeArchivo;
import * as fs from 'fs';
class ValidacionCompararContraPeores {
    constructor() {
        _ValidacionCompararContraPeores_instances.add(this);
        _ValidacionCompararContraPeores_contraseñasComunes.set(this, void 0);
    }
    esValida(clave) {
        if (__classPrivateFieldGet(this, _ValidacionCompararContraPeores_contraseñasComunes, "f") == null) {
            __classPrivateFieldGet(this, _ValidacionCompararContraPeores_instances, "m", _ValidacionCompararContraPeores_leerContraseñasDesdeArchivo).call(this);
        }
        return __classPrivateFieldGet(this, _ValidacionCompararContraPeores_contraseñasComunes, "f").includes(clave);
    }
    ;
    ;
}
_ValidacionCompararContraPeores_contraseñasComunes = new WeakMap(), _ValidacionCompararContraPeores_instances = new WeakSet(), _ValidacionCompararContraPeores_leerContraseñasDesdeArchivo = function _ValidacionCompararContraPeores_leerContraseñasDesdeArchivo() {
    const file = fs.readFileSync("./10000-most-common-passwords.txt", "utf-8");
    __classPrivateFieldSet(this, _ValidacionCompararContraPeores_contraseñasComunes, file.split('\r\n'), "f");
};
//# sourceMappingURL=ValidacionCompararContraPeores.js.map