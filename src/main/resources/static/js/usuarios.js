// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios()
  $('#usuarios').DataTable();
});


async function cargarUsuarios(){

  const request = await  fetch('http://localhost:8090/api/usuarios', {
    method: 'GET',
    headers:getHeaders()
  } );

  const usuarios = await request.json();
  console.log(usuarios)
  
  let listadoHtml = '' 


  for(let usuario of usuarios){
    let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')"  class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let telefonoTexto = usuario.telefono == null ? '-' : usuario.telefono;


    let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>' + usuario.nombre + ' ' + usuario.apellido + '</td><td>'
    + usuario.email+'</td><td>'+telefonoTexto
    + '</td><td>' + botonEliminar + '</td></tr>';
    listadoHtml += usuarioHTML;    
   
  }



  document.querySelector('#usuarios tbody').outerHTML = listadoHtml
}

async function eliminarUsuario(id){

  if(!confirm('¿Desea eliminar el usuario?')){
    return;
  }
  const request = await  fetch('http://localhost:8090/api/usuarios/'+id, {
    method: 'DELETE',
    headers: getHeaders()
  } );
  location.reload();
}

function getHeaders(){
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Autorization' : localStorage.token
  }
}