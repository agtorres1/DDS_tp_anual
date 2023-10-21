document.addEventListener("DOMContentLoaded", function() {
    var editarPerfilButton = document.getElementById("editarPerfil");

    editarPerfilButton.addEventListener("click", function() {
        var userId = editarPerfilButton.getAttribute("data-user-id");

        // Agrega console.log para depuración
        console.log("userId:", userId);

        // Asegúrate de que userId no sea null o indefinido antes de usarlo
        if (userId !== null) {
            // Construye la URL de destino
            var url = "/usuario/" + userId + "/editar";

            // Agrega console.log para depuración
            console.log("url:", url);

            // Redirige al usuario a la nueva URL
            window.location.href = url;
        }
    });
});


