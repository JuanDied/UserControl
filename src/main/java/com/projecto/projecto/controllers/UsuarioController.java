package com.projecto.projecto.controllers;

import com.projecto.projecto.dao.UsuarioDaoImp;
import com.projecto.projecto.models.Usuario;
import com.projecto.projecto.dao.UsuarioDao;
import com.projecto.projecto.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value= "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Autorization") String token ){


        if(!validarToken(token)){
            return null;
        }
        return usuarioDao.getUsuarios();

    }

    @RequestMapping(value= "api/usuarios", method = RequestMethod.POST)
    public void addUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash  = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registar(usuario);

    }


    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id,
                         @RequestHeader(value = "Autorization") String token ) {
        if(!validarToken(token)){
            return ;
        }
        usuarioDao.eliminar(id);
    }

    public boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;

    }




}
