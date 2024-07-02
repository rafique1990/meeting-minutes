package com.example.meetingMinutesdemo.controllers;

import com.example.meetingMinutesdemo.models.TaskStatus;
import com.example.meetingMinutesdemo.services.TaskStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/taskstatus")
public class TaskStatusController {

    @Autowired
    private TaskStatusServiceImpl taskStatusService;


    @GetMapping
    public List<TaskStatus> list() {
        return taskStatusService.getTaskStatusList();
    }

    @GetMapping
    @RequestMapping("{id}")
    public TaskStatus get(@PathVariable Long id) {
        return taskStatusService.getTaskStatusById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskStatus create(@RequestBody final TaskStatus taskStatus) {
        return taskStatusService.createTaskStatus(taskStatus);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {

        taskStatusService.deleteTaskStatusById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public TaskStatus update(@PathVariable Long id, @RequestBody TaskStatus taskStatus) {
        return taskStatusService.updateTaskStatus(id,taskStatus);
    }
}
