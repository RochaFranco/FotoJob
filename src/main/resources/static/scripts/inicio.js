var listItemClass = "list-group-item list-group-item-action";
let filtrosdiv = document.getElementById("filtros_especial");
let filtros = filtrosdiv.children
let filtros_pdiv = document.getElementById("filtros_precio");
let filtros_p = filtros_pdiv.children
let gridContainer = document.getElementById("grid-container");
let fotografos = gridContainer.children

let filtro_def = ["card", "card"];


for (let i = 0; i < filtros.length; i++) {
    filtros[i].onclick = ()=> {

        for (let j = 0; j < filtros.length; j++) {
            filtros[j].className = listItemClass;
        }
        filtros[i].className = listItemClass + " active"
        filtro_def[0] = (filtros[i].id.substring(filtros[i].id.indexOf("_")+1));
        console.log(filtro_def);
        for (let k = 0; k < fotografos.length; k++) {
            if(fotografos[k].className.includes(filtro_def[0]) && fotografos[k].className.includes(filtro_def[1])) {
                show(fotografos[k])
            } else {
                hide(fotografos[k])
            }
        }
    }
}

for (let i = 0; i < filtros_p.length; i++) {
    filtros_p[i].onclick = ()=> {
        for (let j = 0; j < filtros_p.length; j++) {
            filtros_p[j].className = listItemClass;
        }
        filtros_p[i].className = listItemClass + " active"
        filtro_def[1] = (filtros_p[i].id.substring(filtros_p[i].id.indexOf("_")+1));
        console.log(filtro_def);
        for (let k = 0; k < fotografos.length; k++) {
            if(fotografos[k].className.includes(filtro_def[0]) && fotografos[k].className.includes(filtro_def[1])) {
                show(fotografos[k])
            } else {
                hide(fotografos[k])
            }
        }
    }
}



function hide(element) {
    element.setAttribute("hidden", "")
}

function show(element) {
    element.removeAttribute("hidden")
}