package com.example.meetingMinutesdemo.services;
import com.example.meetingMinutesdemo.models.Meeting;
import java.util.List;

public interface MeetingService {
    public abstract  List<Meeting> getMeetings();
    public abstract  Meeting getMeetingById(Long id);
    public abstract  Meeting createMeeting( final Meeting meeting);
    public abstract  void    deleteMeetingById(Long id);
    public abstract  Meeting updateMeeting( Long id, Meeting meeting);

}
