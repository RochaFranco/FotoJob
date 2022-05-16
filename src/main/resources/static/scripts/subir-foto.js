
// Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.6.11/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.6.11/firebase-analytics.js";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyDNahmABgLUuhWnpoMthso_lY7G9SbhUS4",
    authDomain: "prueba-subir-imagenes.firebaseapp.com",
    databaseURL: "https://prueba-subir-imagenes-default-rtdb.firebaseio.com",
    projectId: "prueba-subir-imagenes",
    storageBucket: "prueba-subir-imagenes.appspot.com",
    messagingSenderId: "769231789957",
    appId: "1:769231789957:web:1cf503945020dc7142eeec",
    measurementId: "G-E9ZKEWV9XT"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

import {getStorage , ref as sRef , uploadBytesResumable , getDownloadURL} from "https://www.gstatic.com/firebasejs/9.6.11/firebase-storage.js"
var files = []
var reader = new FileReader();

var fichero = document.getElementById("fichero")
var preview = document.getElementById("preview")
var progresswrapper = document.getElementById("progresswrap")
var progressbar = document.getElementById("progress-bar-up") 
var uploadbtn = document.getElementById("uploadbtn")
var nametag = document.getElementById("nametag")
var imageurlFB = document.getElementById("imageurlFB")
var postbtn = document.getElementById("postbtn")

// ----------------
let cropper = null;
let imageurlFBmin = document.getElementById("imageurlFBmin")
var crop_image = document.getElementById("crop-image")
let modalW = document.getElementById("modalW")
let modalC = document.getElementById("modalC")
var cut = document.getElementById("cut")
var close = document.getElementById("close")

// ----------------

fichero.onchange = e => {
    files = e.target.files;

    var extension = GetFileExt(files[0])
    var name = GetFileName(files[0])

    reader.readAsDataURL(files[0])

    nametag.innerHTML = extension;
    progresswrapper.className = "progress-hidden";
    progressbar.style = `width: 0`;
    uploadbtn.className = "btn btn-secondary";
    postbtn.className = "btn btn-success hidden";

}

reader.onload = function(){
    preview.src = reader.result;
    preview.className = "preview"
}



function GetFileExt(file) {
    var temp = file.name.split('.');
    var ext = temp.slice(temp.length-1,temp.length);
    return '.'+ext[0];
}

function GetFileName(file) {
    var temp = file.name.split('.');
    var fname = temp.slice(0,-1).join('.');
    return fname;
}


async function uploadProcess() {
    progresswrapper.className = "progress"
    progresswrapper.style = "height: 4px"
    var a = Math.floor(Math.random()*10000);
    var b = Math.floor(Math.random()*10000);
    var c = Math.floor(Math.random()*10000);
    var imageUpload = files[0];
    var ImageName = String(a)+"-"+String(b)+"-"+String(c)+nametag.innerHTML;

    const metaData = {
        contentType: imageUpload.type
    }

    const storage = getStorage();

    const storageRef = sRef(storage, "imagenes/"+ImageName)

    const uploadTask = uploadBytesResumable(storageRef, imageUpload, metaData)

    uploadTask.on("state-changed", (snapshot)=>{
        
        var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
        progressbar.style = `width: ${progress}%`;
        if(progress == 100){
            uploadbtn.className = "btn btn-secondary hidden";
            
        }
    },
    (error)=>{
        alert("error: no se subio la imagen");
    },
    ()=>{
        getDownloadURL(uploadTask.snapshot.ref).then((downloadURL)=>{
            imageurlFB.outerHTML = `<input type="text" id="imageurlFB" value="${downloadURL}" name="imageUrl" hidden></input>`;
            // =============
            let image = document.getElementById('img-cropper')
            let input = document.getElementById('fichero')
            
            let archivos = input.files
            let extensiones = input.value.substring(input.value.lastIndexOf('.'), input.value.lenght)
            

            if(!archivos || !archivos.length){        
                image.src = "";
                input.value = "";
                
            } else if((input.getAttribute('accept').split(',').indexOf(extensiones) < 0) && (input.getAttribute('accept').split(',').indexOf(extensiones.toLowerCase()) < 0)){
                alert('Debes seleccionar una imagen')
                input.value = "";

            } else {
                let imagenUrl = URL.createObjectURL(archivos[0])
                image.src = imagenUrl

                cropper = new Cropper(image, {
                    aspectRatio: 1, // es la proporción en la que queremos que recorte en este caso 1:1
                    preview: '.img-sample', // contenedor donde se va a ir viendo en tiempo real la imagen cortada
                    zoomable: false, //Para que no haga zoom 
                    viewMode: 1, //Para que no estire la imagen al contenedor
                    responsive: false, //Para que no reacomode con zoom la imagen al contenedor
                    dragMode: 'none', //Para que al arrastrar no haga nada
                    ready(){ // metodo cuando cropper ya este activo, le ponemos el alto y el ancho del contenedor de cropper al 100%
                        document.querySelector('.cropper-container').style.width = '100%'
                        document.querySelector('.cropper-container').style.height = '100%'
                    }
                })

                modalW.className = 'modal active';
                modalC.className = 'modal-content active'
                

            }
            // =============
        })
        postbtn.ariaDisabled = false;
    })
}

// ===========
close.addEventListener("click", ()=>{
    let image = document.getElementById('img-cropper')
    let input = document.getElementById('fichero')

    image.src = "";
    input.value = "";

    cropper.destroy()

    modalW.className = 'modal ';
    modalC.className = 'modal-content ';       
})

cut.addEventListener("click", ()=>{
    
    let canva = cropper.getCroppedCanvas()
    let image = document.getElementById('img-cropper')
    let input = document.getElementById('fichero')

    canva.toBlob(function(blob){
        let url_cut = URL.createObjectURL(blob)
        crop_image.src = url_cut;

        // --------
        var a = Math.floor(Math.random()*10000);
        var b = Math.floor(Math.random()*10000);
        var c = Math.floor(Math.random()*10000);
        var imageUpload = blob;
        var ImageName = String(a)+"-"+String(b)+"-"+String(c)+".jpg";

        const metaData = {
            contentType: "image/jpeg"
        }

        const storage = getStorage();

        const storageRef = sRef(storage, "imagenes/"+ImageName)

        const uploadTask = uploadBytesResumable(storageRef, imageUpload, metaData)

        uploadTask.on("state-changed", (snapshot)=>{
            
            // var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
            // progressbar.style = `width: ${progress}%`;
            // if(progress == 100){
            //     uploadbtn.className = "btn btn-secondary hidden";
            //     postbtn.className = "btn btn-success";
            // }
        },
        (error)=>{
            alert("error: no se subio la imagen");
        },
        ()=>{
            getDownloadURL(uploadTask.snapshot.ref).then((downloadURL)=>{
                imageurlFBmin.outerHTML = `<input type="text" id="imageurlFBmin" value="${downloadURL}" name="imageUrlMin" hidden></input>`;
                postbtn.className = "btn btn-success";
            })
            
        })
        // --------
    })

    image.src = "";
    input.value = "";

    cropper.destroy()

    modalW.className = 'modal ';
    modalC.className = 'modal-content '

}) 
// ===========


uploadbtn.onclick = uploadProcess;

