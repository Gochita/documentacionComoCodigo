package com.Ejercicio.APIRestUsuarios.repositories;

import com.Ejercicio.APIRestUsuarios.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    public abstract ArrayList<UsuarioModel> findByPriority(Integer priority);
}
