package com.ann.BootTrackActivityTracker.service;

import com.ann.BootTrackActivityTracker.dto.TaskDTO;
import com.ann.BootTrackActivityTracker.entity.Task;
import com.ann.BootTrackActivityTracker.exception.ResourceNotFoundException;

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
