package dev.diemigo.usuarios.assembler;


import dev.diemigo.usuarios.controller.UsuarioController;
import dev.diemigo.usuarios.dto.UsuarioRespuestaDTO;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler <UsuarioRespuestaDTO, UsuarioRespuestaDTO>{

    @Override
    public UsuarioRespuestaDTO toModel(UsuarioRespuestaDTO dto){
        dto.add(linkTo (methodOn( UsuarioController.class).getUsuarioById(dto.getId())).withSelfRel());
        dto.add(linkTo(methodOn(UsuarioController.class).getUsuarios()).withRel("Lista completa"));
        return dto;
    }
}
