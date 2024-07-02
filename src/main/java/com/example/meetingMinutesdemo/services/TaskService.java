package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.Task;

import java.util.List;

public interface TaskService {

    public abstract List<Task> getTaskList();
    public abstract  Task getTaskById(Long id);
    public abstract  Task createTask( final  Task task);
    public abstract  void   deleteTaskById(Long id);
    public abstract  Task updateTask( Long id, Task task);
}
