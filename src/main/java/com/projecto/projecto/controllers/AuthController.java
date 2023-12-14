package com.projecto.projecto.controllers;

import com.projecto.projecto.dao.UsuarioDao;
import com.projecto.projecto.models.Usuario;
import com.projecto.projecto.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value= "api/login", method = RequestMethod.POST)
    public String loginUsuario(@RequestBody Usuario usuario){

        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);

        System.out.println("usuariologuado"+ usuarioLogueado.getEmail());
        System.out.println("usuariologpss"+ usuarioLogueado.getPassword());
        if(usuarioLogueado != null){
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            System.out.println("tokenjwt"+tokenJwt);
            return tokenJwt;
        }
        else {
            return "FAIL";
        }

    }
}
