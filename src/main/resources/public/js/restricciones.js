document.addEventListener("DOMContentLoaded", function () {
    const medioSelect = document.getElementById("medioSelect");
    const whatsappDiv = document.getElementById("whatsappDiv");
    const emailDiv = document.getElementById("emailDiv");

    medioSelect.addEventListener("change", function () {
        if (medioSelect.value === "whatsapp") {
            whatsappDiv.style.display = "block";
            emailDiv.style.display = "none";
        }
        else {
            whatsappDiv.style.display = "none";
            emailDiv.style.display = "none";
        }
    });
});