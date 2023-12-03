async function cargarMunicipiosYDepartamentos() {
    // Obtener el valor seleccionado en el campo de provincias
    var provinciaId = document.getElementById("provincia").value;
    console.log(provinciaId)


    var provinciaId = document.getElementById("provincia").value;

    // Realizar la llamada Fetch para obtener municipios
    fetch(`https://apis.datos.gob.ar/georef/api/municipios?provincia=${provinciaId}&campos=id,nombre&max=200`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error en la solicitud de municipios: ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            // Verificar si 'data' tiene la propiedad 'municipios' que es una lista
            if (data && Array.isArray(data.municipios)) {
                cargarSelectMunicipios(data.municipios);
            } else {
                console.error('La respuesta de municipios no tiene la propiedad esperada o no es una lista:', data);
            }
        })
        .catch(error => {
            console.error('Error al cargar municipios:', error.message);
        });

    fetch(`https://apis.datos.gob.ar/georef/api/departamentos?provincia=${provinciaId}&campos=id,nombre&max=135`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error en la solicitud de departamentos: ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            // Verificar si 'data' tiene la propiedad 'municipios' que es una lista
            if (data && Array.isArray(data.departamentos)) {
                cargarSelectDepartamentos(data.departamentos);
            } else {
                console.error('La respuesta de departamentos no tiene la propiedad esperada o no es una lista:', data);
            }
        })
        .catch(error => {
            console.error('Error al cargar departamentos:', error.message);
        });
}

function cargarSelectMunicipios(municipios) {
    // Obtener el elemento select de municipios
    var selectMunicipio = document.getElementById("municipio");

    // Limpiar las opciones anteriores
    selectMunicipio.innerHTML = "";

    // Agregar las nuevas opciones
    municipios.forEach(function(municipio) {
        var option = document.createElement("option");
        option.value = municipio.nombre;
        option.text = municipio.nombre;
        selectMunicipio.add(option);
    });
}

function cargarSelectDepartamentos(departamentos) {
    // Obtener el elemento select de departamentos
    var selectDepartamento = document.getElementById("departamento");

    // Limpiar las opciones anteriores
    selectDepartamento.innerHTML = "";

    // Agregar las nuevas opciones
    departamentos.forEach(function(departamento) {
        var option = document.createElement("option");
        option.value = departamento.nombre;
        option.text = departamento.nombre;
        selectDepartamento.add(option);
    });
}