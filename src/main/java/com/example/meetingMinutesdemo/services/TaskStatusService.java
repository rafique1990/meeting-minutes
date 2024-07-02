package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.TaskStatus;

import java.util.List;

public interface TaskStatusService {

    public abstract List<TaskStatus> getTaskStatusList();
    public abstract  TaskStatus getTaskStatusById(Long id);
    public abstract  TaskStatus createTaskStatus( final  TaskStatus taskStatus);
    public abstract  void   deleteTaskStatusById(Long id);
    public abstract  TaskStatus updateTaskStatus( Long id, TaskStatus taskStatus);
}
