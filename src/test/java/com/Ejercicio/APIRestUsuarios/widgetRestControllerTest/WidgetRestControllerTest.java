package com.Ejercicio.APIRestUsuarios.widgetRestControllerTest;

import com.Ejercicio.APIRestUsuarios.models.UsuarioModel;
import com.Ejercicio.APIRestUsuarios.services.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WidgetRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsuarioService service;


    @Test
    @DisplayName("GET/Usuarios success")
    void testGetUsuariosSuccess() throws Exception {
        mockMvc.perform(get("/usuario"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION, ""))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", is(7)))
                .andExpect((ResultMatcher) jsonPath("$[1].id", is(8)));
    }

    @Test
    @DisplayName("POST/Usuario-Nuevo success")
    void testPostUsuariosSuccess() throws Exception {
        UsuarioModel usuario = new UsuarioModel("Juan Bautista", "jbautista@gmail.com", 1);
        mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(usuario)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION, "/usuario"))
                .andExpect((ResultMatcher) jsonPath("$.name", is("Juan Bautista")));

    }

    @Test
    @DisplayName("POST/Usuario-Actualizar success")
    void testPostUsuarioToUpdate() throws Exception {
        UsuarioModel usuario = new UsuarioModel("Tobias", "tobi@hotmail.com", 5);
        usuario.setId(1);
        mockMvc.perform(post("/usuario/actualizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(usuario)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION, "/usuario/actualizar"))
                .andExpect((ResultMatcher) jsonPath("$.name", is("Tobias")));
    }

    @Test
    @DisplayName("GET/Usuario-ById success")
    void testGetUsuarioById() throws Exception {
        mockMvc.perform(get("/usuario/7"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string(HttpHeaders.LOCATION, ""))
                .andExpect(jsonPath("/usuario", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", is(7)));
    }
}
