package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.TaskPriority;
import com.example.meetingMinutesdemo.repositories.TaskPriorityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskPriorityServiceImpl implements  TaskPriorityService {
    @Autowired
    private TaskPriorityRepository taskPriorityRepository;

    public TaskPriorityServiceImpl() {
    }

    @Override
    public List<TaskPriority> getTaskPriorityList() {
        return taskPriorityRepository.findAll();
    }

    @Override
    public TaskPriority getTaskPriorityById(Long id) {
        return taskPriorityRepository.getOne(id);
    }

    @Override
    public TaskPriority createTaskPriority(TaskPriority taskPriority) {
        return taskPriorityRepository.saveAndFlush(taskPriority);
    }

    @Override
    public void deleteTaskPriorityById(Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        taskPriorityRepository.deleteById(id);
    }

    @Override
    public TaskPriority updateTaskPriority(Long id, TaskPriority taskPriority) {
        TaskPriority existingTaskPriority = taskPriorityRepository.getOne(id);
        BeanUtils.copyProperties(taskPriority, existingTaskPriority, "task_priority_id");
        return taskPriorityRepository.saveAndFlush(existingTaskPriority);
    }
}
