package com.example.meetingMinutesdemo.services;
import com.example.meetingMinutesdemo.models.MeetingAssignsTasks;


import java.util.List;

public interface MeetingAssignsTasksService {

    public abstract  List<MeetingAssignsTasks> getMeetingAssignsTasksList();
    public abstract  MeetingAssignsTasks getMeetingAssignsTasksById(Long id);
    public abstract  MeetingAssignsTasks createMeetingAssignsTasks( final MeetingAssignsTasks meetingAssignsTasks);
    public abstract  void    deleteMeetingAssignsTasksById(Long id);
    public abstract  MeetingAssignsTasks updateMeetingAssignsTasks( Long id, MeetingAssignsTasks meetingAssignsTasks);
}
