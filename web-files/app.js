var id = 1;
let url = "/change-img";

function changeAndLoadImg (){
    //Rotates between 4 main images
    if(id < 4){
        id += 1;
    }else{
        id = 1;
    }
    // Modifies the API rest query
    url = url + "/" + id;

    // Perform the request
    fetch(url, {method: 'GET'})
        .then(x => x.blob())
        .then(y => reAsignImg(y));
    
    url = "/change-img";
}

// This re-asign the content of the img label
function reAsignImg(blob){
    const imageURL = URL.createObjectURL(blob);
    const imageElement = document.getElementById("place-holder");
    imageElement.src = imageURL;
}

document.getElementById('daButton').onclick = function() {
    changeAndLoadImg();
};