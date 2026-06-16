package com.br.enterprise.unitetests;

import com.br.enterprise.dto.TaskDTO;
import com.br.enterprise.model.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MockTask {

    public Task mockEntity() {
        return mockEntity(0);
    }

    public TaskDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Task> mockEntityList() {

        List<Task> tasks = new ArrayList<Task>();

        for (int i = 0; i <14; i++){
            tasks.add(mockEntity(i));
        }

        return tasks;
    }

    public List<TaskDTO> mockDTOList() {

        return IntStream.range(0, 14)
                .mapToObj(this::mockDTO)
                .toList();
    }

    public Task mockEntity(Integer number){
        Task entity = new Task();

        entity.setId(number.longValue());
        entity.setTitle("Title Test " + number);
        entity.setDescription("Description Test " + number);
        entity.setCompleted(number % 2 != 0);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public TaskDTO mockDTO(Integer number){
        TaskDTO dto = new TaskDTO();

        dto.setId(number.longValue());
        dto.setTitle("Title Test " + number);
        dto.setDescription("Description Test " + number);
        dto.setCompleted(number % 2 != 0);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setUpdatedAt(LocalDateTime.now());
        return dto;
    }
}
