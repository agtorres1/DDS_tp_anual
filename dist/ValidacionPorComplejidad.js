class ValidacionPorComplejidad {
    esValida(clave) {
        return RegExp("[A-Z]").test(clave) &&
            RegExp("[a-z]").test(clave) &&
            RegExp("[0-9]").test(clave);
    }
    ;
}
export {};
//# sourceMappingURL=ValidacionPorComplejidad.js.map