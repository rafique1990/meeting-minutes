package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.Project;
import com.example.meetingMinutesdemo.repositories.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements  ProjectService {
    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public List<Project> getProjectList() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.getOne(id);
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public void deleteProjectById(Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        projectRepository.deleteById(id);
    }

    @Override
    public Project updateProject(Long id, Project project) {
        Project existingProject = projectRepository.getOne(id);
        BeanUtils.copyProperties(project, existingProject, "project_id");
        return projectRepository.saveAndFlush(existingProject);
    }
}
