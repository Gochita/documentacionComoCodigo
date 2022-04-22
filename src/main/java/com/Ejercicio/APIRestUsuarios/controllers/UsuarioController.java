package com.Ejercicio.APIRestUsuarios.controllers;

import com.Ejercicio.APIRestUsuarios.models.UsuarioModel;
import com.Ejercicio.APIRestUsuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @DeleteMapping(path = "/deleteAll")
    public String deleteAll(){
        this.usuarioService.eliminarTodo();
        return "Se borraron todos los datos de la tabla. \t \t D:";
    }

    @GetMapping()
    public ArrayList<UsuarioModel> traerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }



    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> getById(@PathVariable("id") Long id){
        return this.usuarioService.obtenerPorId(id);
    }

    @GetMapping(path = "/query")
    public ArrayList<UsuarioModel> getByPriority(@RequestParam("prioridad") Integer prioridad){
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }

    @PostMapping()
    public UsuarioModel save(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok =  this.usuarioService.eliminarUsuario(id);
        if (ok){
            return  id.toString()+ " deleted." ;
        }
        return   id.toString() +"Not found";
    }

    @PostMapping(path = "/actualizar")
    public UsuarioModel update(@RequestBody UsuarioModel usuario){
        if (usuario.toString().contains("id")){
            return this.usuarioService.actualizarUsuario(usuario);
        }
        return this.save(usuario);
    }


}
