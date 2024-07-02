package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.TaskStatus;
import com.example.meetingMinutesdemo.repositories.TaskStatusRepositroy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskStatusServiceImpl implements  TaskStatusService {
    @Autowired
    private TaskStatusRepositroy taskStatusRepositroy;

    public TaskStatusServiceImpl() {
    }

    @Override
    public List<TaskStatus> getTaskStatusList() {
        return taskStatusRepositroy.findAll();
    }

    @Override
    public TaskStatus getTaskStatusById(Long id) {
        return taskStatusRepositroy.getOne(id);
    }

    @Override
    public TaskStatus createTaskStatus(TaskStatus taskStatus) {
        return taskStatusRepositroy.saveAndFlush(taskStatus);
    }

    @Override
    public void deleteTaskStatusById(Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        taskStatusRepositroy.deleteById(id);
    }

    @Override
    public TaskStatus updateTaskStatus(Long id, TaskStatus taskStatus) {
        TaskStatus existingTaskStatus = taskStatusRepositroy.getOne(id);
        BeanUtils.copyProperties(taskStatus, existingTaskStatus, "task_status_id");
        return taskStatusRepositroy.saveAndFlush(existingTaskStatus);
    }
}
