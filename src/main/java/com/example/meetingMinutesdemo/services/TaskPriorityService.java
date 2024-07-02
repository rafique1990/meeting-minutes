package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.TaskPriority;

import java.util.List;

public interface TaskPriorityService {

    public abstract List<TaskPriority> getTaskPriorityList();
    public abstract  TaskPriority getTaskPriorityById(Long id);
    public abstract  TaskPriority createTaskPriority( final  TaskPriority taskPriority);
    public abstract  void   deleteTaskPriorityById(Long id);
    public abstract  TaskPriority updateTaskPriority( Long id, TaskPriority taskPriority);
}
