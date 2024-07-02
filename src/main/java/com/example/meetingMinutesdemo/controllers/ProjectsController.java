package com.example.meetingMinutesdemo.controllers;

import com.example.meetingMinutesdemo.models.Project;
import com.example.meetingMinutesdemo.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectsController {

    @Autowired
    private ProjectServiceImpl projectService;


    @GetMapping
    public List<Project> list() {
        return projectService.getProjectList();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Project get(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody final Project project) {
        return projectService.createProject(project);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        projectService.deleteProjectById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Project update(@PathVariable Long id, @RequestBody Project project) {
        return projectService.updateProject( id,project);
    }
}
