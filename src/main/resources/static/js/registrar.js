// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on ready
  });
  
  
  async function registrarUsuarios(){
    
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value
    datos.apellido = document.getElementById('txtApellido').value
    datos.email = document.getElementById('txtEmail').value
    datos.telefono = document.getElementById('txtPhone').value
    datos.password = document.getElementById('txtPassword').value
    let passwordc = document.getElementById('txtRepetirPassword').value

    if(datos.password != passwordc){
        alert('Contraseñas no conciden')
        return
    }


    const request = await  fetch('http://localhost:8090/api/usuarios', {
      method: 'POST',
      headers:{
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
    } );
  
    alert("Ahora estás registrado")
    window.location.href = 'login.html'    
 
  }
  
