
let cat = ["Eventos", "Marketing", "Paisajes", "Retratos"]
let opcionesEsp = document.getElementById("inputEspecialidad")
let rango = ["$$","$$$","$$$$"]
let opcionesRango = document.getElementById("inputRangoPrecio")

for (const catg of cat) {
    opcionesEsp.innerHTML = opcionesEsp.innerHTML + `<option value="${catg}">${catg}</option>`
}

for (const rang of rango) {
    opcionesRango.innerHTML = opcionesRango.innerHTML + `<option value="${rang}">${rang}</option>`
}