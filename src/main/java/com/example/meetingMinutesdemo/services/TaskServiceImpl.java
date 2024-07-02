package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.Task;
import com.example.meetingMinutesdemo.repositories.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskServiceImpl implements  TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskServiceImpl() {
    }

    @Override
    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getOne(id);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        taskRepository.deleteById(id);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task existingTask = taskRepository.getOne(id);
        BeanUtils.copyProperties(task, existingTask, "task_id");
        return taskRepository.saveAndFlush(existingTask);
    }
}
