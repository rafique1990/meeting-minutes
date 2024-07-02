package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.Project;

import java.util.List;

public interface ProjectService {

    public abstract List<Project> getProjectList();
    public abstract  Project getProjectById(Long id);
    public abstract  Project createProject( final  Project project);
    public abstract  void   deleteProjectById(Long id);
    public abstract  Project updateProject( Long id, Project project);
}
