package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.MeetingAssignsTasks;
import com.example.meetingMinutesdemo.repositories.MeetingAssignsTasksRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Data
public class MeetingAssignsTasksServiceImpl implements  MeetingAssignsTasksService {

    @Autowired
    private MeetingAssignsTasksRepository meetingAssignsTasksRepository;

    @Override
    public List<MeetingAssignsTasks> getMeetingAssignsTasksList() {
        return meetingAssignsTasksRepository.findAll();
    }

    @Override
    public MeetingAssignsTasks getMeetingAssignsTasksById(Long id) {
        return meetingAssignsTasksRepository.getOne(id);
    }

    @Override
    public MeetingAssignsTasks createMeetingAssignsTasks(MeetingAssignsTasks meetingAssignsTasks) {

        return meetingAssignsTasksRepository.saveAndFlush(meetingAssignsTasks);
    }

    @Override
    public void deleteMeetingAssignsTasksById(Long id) {
        meetingAssignsTasksRepository.deleteById(id);
    }

    @Override
    public MeetingAssignsTasks updateMeetingAssignsTasks(Long id, MeetingAssignsTasks meetingAssignsTask) {
        MeetingAssignsTasks existingMeetingAssignsTask = meetingAssignsTasksRepository.getOne(id);
        BeanUtils.copyProperties(meetingAssignsTask, existingMeetingAssignsTask, "meeting_assigns_task_id");
        return meetingAssignsTasksRepository.saveAndFlush(existingMeetingAssignsTask);
    }
}
