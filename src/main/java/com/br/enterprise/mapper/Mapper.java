package com.br.enterprise.mapper;

import com.br.enterprise.dto.TaskDTO;
import com.br.enterprise.model.Task;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public TaskDTO convertEntityToDTO(Task entity){

        TaskDTO dto = new TaskDTO();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCompleted(entity.isCompleted());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public Task convertDTOToEntity(TaskDTO dto){

        Task entity = new Task();

        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCompleted(dto.isCompleted());

        return entity;
    }
}
