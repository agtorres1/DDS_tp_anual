const body = document.querySelector("body");
const darkLight = document.querySelector("#darkLight");
const sidebar = document.querySelector(".sidebar");
const submenuItems = document.querySelectorAll(".submenu_item");
const sidebarOpen = document.querySelector("#sidebarOpen");
const sidebarClose = document.querySelector(".collapse_sidebar");
const sidebarExpand = document.querySelector(".expand_sidebar");
sidebarOpen.addEventListener("click", () => sidebar.classList.toggle("close"));

sidebarClose.addEventListener("click", () => {
    sidebar.classList.add("close", "hoverable");
});
sidebarExpand.addEventListener("click", () => {
    sidebar.classList.remove("close", "hoverable");
});

sidebar.addEventListener("mouseenter", () => {
    if (sidebar.classList.contains("hoverable")) {
        sidebar.classList.remove("close");
    }
});
sidebar.addEventListener("mouseleave", () => {
    if (sidebar.classList.contains("hoverable")) {
        sidebar.classList.add("close");
    }
});

darkLight.addEventListener("click", () => {
    body.classList.toggle("dark");
    if (body.classList.contains("dark")) {
        document.setI
        darkLight.classList.replace("bx-sun", "bx-moon");
    } else {
        darkLight.classList.replace("bx-moon", "bx-sun");
    }
});

submenuItems.forEach((item, index) => {
    item.addEventListener("click", () => {
        item.classList.toggle("show_submenu");
        submenuItems.forEach((item2, index2) => {
            if (index !== index2) {
                item2.classList.remove("show_submenu");
            }
        });
    });
});

if (window.innerWidth < 768) {
    sidebar.classList.add("close");
} else {
    sidebar.classList.remove("close");
}




// <!-- JavaScript Bundle with Popper -->
//
// src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
// integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
// crossorigin="anonymous"
//
// src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/js/all.min.js"

document.getElementById("obtenerUbicacion").addEventListener("click", function() {
    obtenerUbicacion();
});

function obtenerUbicacion() {
    if (navigator.geolocation) {
        var opciones = {
            enableHighAccuracy: true, // Habilita el uso del GPS para obtener una ubicación precisa
            timeout: 5000, // Tiempo máximo (en milisegundos) para esperar una respuesta del servicio de geolocalización
            maximumAge: 0 // Tiempo máximo (en milisegundos) para mantener en caché la ubicación. 0 significa no usar caché.
        };

        navigator.geolocation.getCurrentPosition(
            function(position) {
                var latitud = position.coords.latitude;
                var longitud = position.coords.longitude;
                // Enviar la ubicación al servidor
                enviarUbicacion(latitud, longitud);
            },
            function(error) {
                console.error("Error al obtener la ubicación: " + error.message);
            },
            opciones
        );
    } else {
        alert("Tu navegador no admite la geolocalización.");
    }
}
function enviarUbicacion(latitud, longitud) {
    // Configura los datos que quieres enviar al servidor
    var data = {
        latitud: latitud,
        longitud: longitud
    };
    console.log(data);

    fetch('/ubicacion', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Respuesta del servidor:', data);
            // Haz algo con la respuesta del servidor si es necesario
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

