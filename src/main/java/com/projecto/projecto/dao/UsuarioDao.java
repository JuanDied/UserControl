package com.projecto.projecto.dao;

import com.projecto.projecto.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios() ;

    void eliminar(Long id);

    void registar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
