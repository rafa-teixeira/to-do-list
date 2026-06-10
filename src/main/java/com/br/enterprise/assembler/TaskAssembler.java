package com.br.enterprise.assembler;

import com.br.enterprise.controller.TaskController;
import com.br.enterprise.dto.TaskDTO;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskAssembler {

    public TaskDTO toModel(TaskDTO dto){

        dto.add(
                linkTo(methodOn(TaskController.class)
                        .findById(dto.getId()))
                        .withSelfRel()
        );
        return dto;
    }
}