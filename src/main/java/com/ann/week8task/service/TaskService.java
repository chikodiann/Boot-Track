package com.ann.week8task.service;

import com.ann.week8task.dto.TaskDTO;
import com.ann.week8task.entity.Task;
import com.ann.week8task.exception.ResourceNotFoundException;

import java.util.List;

public interface TaskService {
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    List<Task> getTasksByStatus(String status);
    List<Task> getTasksBySearch(String query);
    Task saveTask(TaskDTO taskDTO);

    Task updateTask(Long id, TaskDTO taskDTO) throws ResourceNotFoundException;
    void deleteTask(Long id) throws ResourceNotFoundException;
}
