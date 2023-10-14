$(document).ready(function () {
    $('#registroForm').submit(function (e) {
        let contrasenia = $('#contrasenia').val();
        let confirmarContrasenia = $('#confirmarContrasenia').val();


        const mayusculaER = /[A-Z]/;
        const minusculaER = /[a-z]/;
        const digitoER = /[0-9]/;

        let mensajesError = [];

        if (!mayusculaER.test(contrasenia)) {
            mensajesError.push('<li>Debe contener al menos una letra mayúscula.</li>');
        }
        if (!minusculaER.test(contrasenia)) {
            mensajesError.push('<li>Debe contener al menos una letra minúscula.</li>');
        }
        if (!digitoER.test(contrasenia)) {
            mensajesError.push('<li>Debe contener al menos un número.</li>');
        }
        if (contrasenia.length < 9) {
            mensajesError.push('<li>Debe contener al menos 9 caracteres.</li>');
        }

        if (mensajesError.length > 0) {
            e.preventDefault();
            $('#errorContrasenia').html(mensajesError.join(' '));
        }
        else if (contrasenia !== confirmarContrasenia) {
            e.preventDefault();
            $('#errorContrasenia').html('<li>Las contraseñas no coinciden</li>');
        }
        else {
            $('#errorContrasenia').html('');
        }
    });
});