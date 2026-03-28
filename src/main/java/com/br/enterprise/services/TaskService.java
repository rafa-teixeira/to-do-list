package com.br.enterprise.services;

import com.br.enterprise.dto.TaskDTO;
import com.br.enterprise.exception.ResourceNotFoundException;
import com.br.enterprise.mapper.Mapper;
import com.br.enterprise.model.Task;
import com.br.enterprise.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TaskService {

    private final Logger logger =Logger.getLogger(TaskService.class.getName());

    TaskRepository repository;
    Mapper mapper;

    public TaskService(TaskRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TaskDTO> findAll(){
        logger.info("Finding all tasks...");

        List<Task> entities = repository.findAll();

        return entities.stream()
                .map(mapper::convertEntityToDTO)
                .toList();
    }

    public TaskDTO findById(Long id){
        logger.info("Finding a task by ID...");

        Task entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No tasks were found for the provided ID."));

        return mapper.convertEntityToDTO(entity);
    }

    public TaskDTO create(TaskDTO dto){
        logger.info("Creating a task...");

        Task entity = mapper.convertDTOToEntity(dto);

        entity.setId(null);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        Task savedEntity = repository.save(entity);
        return mapper.convertEntityToDTO(savedEntity);
    }

    public TaskDTO update(TaskDTO dto){
        logger.info("Updating your task...");

        Task entity = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No tasks were found for the provided ID."));

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCompleted(dto.isCompleted());

        entity.setUpdatedAt(java.time.LocalDateTime.now());

        //mapper ao final para que createdAt não seja alterado.
        return mapper.convertEntityToDTO(repository.save(entity));
    }

    public void delete(Long id){
        logger.info("Deleting a task for the provided ID...");

        Task entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No tasks were found for the provided ID."));

        repository.delete(entity);
    }
}
