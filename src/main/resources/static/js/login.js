// Call the  jQuery plugin
$(document).ready(function() {
    //on ready
  });
  
  
  async function loginUser(){
    
    let datos ={}
    datos.email = document.getElementById('txtEmail').value
    datos.password = document.getElementById('txtPassword').value



    const request = await  fetch('http://localhost:8090/api/login', {
      method: 'POST',
      headers:{
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
    } );
  
    const response = await request.text()
    if(response != 'FAIL'){
        localStorage.token = response
        localStorage.email = datos.email
        window.location.href = 'usuarios.html'
    }
    else{
        alert('Usuario no existe')
    }
  
    
 
  }
  