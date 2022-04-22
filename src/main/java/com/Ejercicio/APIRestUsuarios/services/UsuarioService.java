package com.Ejercicio.APIRestUsuarios.services;

import com.Ejercicio.APIRestUsuarios.models.UsuarioModel;
import com.Ejercicio.APIRestUsuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();

    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long id){
        return  usuarioRepository.findById(id);
    }

    public ArrayList<UsuarioModel> obtenerPorPrioridad(Integer prioridad){
        return usuarioRepository.findByPriority(prioridad);
    }

    public boolean eliminarUsuario(Long id){
        try {
            usuarioRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

    public UsuarioModel actualizarUsuario(UsuarioModel usuario){
        //Long curr_ID = usuario.getId();
        return (this.usuarioRepository.save(usuario));
    }

    public void eliminarTodo(){
        this.usuarioRepository.deleteAll();
    }
}
