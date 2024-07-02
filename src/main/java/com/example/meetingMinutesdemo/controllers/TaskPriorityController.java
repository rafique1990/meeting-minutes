package com.example.meetingMinutesdemo.controllers;


import com.example.meetingMinutesdemo.models.TaskPriority;
import com.example.meetingMinutesdemo.services.TaskPriorityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/taskpriority")
public class TaskPriorityController {

    @Autowired
    private TaskPriorityServiceImpl taskPriorityService;


    @GetMapping
    public List<TaskPriority> list() {
        return taskPriorityService.getTaskPriorityList();
    }

    @GetMapping
    @RequestMapping("{id}")
    public TaskPriority get(@PathVariable Long id) {
        return taskPriorityService.getTaskPriorityById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskPriority create(@RequestBody final TaskPriority taskPriority) {
        return taskPriorityService.createTaskPriority(taskPriority);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        taskPriorityService.deleteTaskPriorityById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public TaskPriority update(@PathVariable Long id, @RequestBody TaskPriority taskPriority) {
        return taskPriorityService.updateTaskPriority(id,taskPriority);
    }
}
