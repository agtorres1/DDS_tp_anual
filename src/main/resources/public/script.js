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

src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
crossorigin="anonymous"

src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/js/all.min.js"

let map, infoWindow;

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: -34.397, lng: 150.644 },
        zoom: 15,
    });
    infoWindow = new google.maps.InfoWindow();

    const locationButton = document.createElement("button");

    locationButton.textContent = "Obtener incidentes cercanos";
    locationButton.classList.add("custom-map-control-button");
    map.controls[google.maps.ControlPosition.TOP_CENTER].push(
        locationButton
    );
    locationButton.addEventListener("click", () => {
        // Try HTML5 geolocation.
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    const pos = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude,

                    };
                    enviarUbicacion(pos.lat, pos.lng);
                    infoWindow.setPosition(pos);
                    infoWindow.setContent("Ubicacion detectada.");
                    infoWindow.open(map);
                    map.setCenter(pos);
                },
                () => {
                    handleLocationError(true, infoWindow, map.getCenter());
                },
            );
        } else {
            // Browser doesn't support Geolocation
            handleLocationError(false, infoWindow, map.getCenter());
        }
    });
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(
        browserHasGeolocation
            ? "Error: The Geolocation service failed."
            : "Error: Your browser doesn't support geolocation."
    );
    infoWindow.open(map);
}

window.initMap = initMap;
function enviarUbicacion(latitud, longitud) {
    // Configura los datos que quieres enviar al servidor
    var data = {
        latitud: latitud,
        longitud: longitud
    };
    console.log(data);

    fetch('/revisarIncidentes', {
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

