
let cat = ["Eventos", "Marketing", "Paisajes", "Retratos"]
let opciones = document.getElementById("inputEspecialidad")

for (const catg of cat) {
    opciones.innerHTML = opciones.innerHTML + `<option value="">${catg}</option>`
}

