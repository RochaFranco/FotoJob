

let cat = ["eventos", "moda", "foto-producto", "books" , "boudoir", "pets"]
let opcionesEsp = document.getElementById("inputEspecialidad")
let rango = ["barato","medio","caro"]
let opcionesRango = document.getElementById("inputRangoPrecio")

for (const catg of cat) {
    opcionesEsp.innerHTML = opcionesEsp.innerHTML + `<option value="${catg}">${catg}</option>`
}

for (const rang of rango) {
    opcionesRango.innerHTML = opcionesRango.innerHTML + `<option value="${rang}">${rang}</option>`
}
