package com.example.meetingMinutesdemo.controllers;


import com.example.meetingMinutesdemo.models.Task;
import com.example.meetingMinutesdemo.services.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TasksController {


    @Autowired
    private TaskServiceImpl taskService;


    @GetMapping
    public List<Task> list() {
        return taskService.getTaskList();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Task get(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody final Task task) {
        return taskService.createTask(task);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        taskService.deleteTaskById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id,task);
    }
//    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id) {
//
//        return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
//    }
}
