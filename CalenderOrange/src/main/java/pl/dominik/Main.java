package pl.dominik;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class Main {
    private static final int MEETING_DURATION_MINUTES = 30;

    public static void main(String[] args) {
        //First Person
        Meeting meeting1 = new Meeting(LocalTime.of(9, 0), LocalTime.of(10, 30));
        Meeting meeting2 = new Meeting(LocalTime.of(12, 0), LocalTime.of(13, 0));
        Meeting meeting3 = new Meeting(LocalTime.of(16, 0), LocalTime.of(18, 0));
        List<Meeting> meetingList1 = Arrays.asList(meeting1, meeting2, meeting3);
        WorkCalendar calender1 = new WorkCalendar(LocalTime.of(9, 0), LocalTime.of(19, 55), meetingList1);

        //Second Person
        Meeting meeting4 = new Meeting(LocalTime.of(10, 0), LocalTime.of(11, 30));
        Meeting meeting5 = new Meeting(LocalTime.of(12, 30), LocalTime.of(14, 30));
        Meeting meeting6 = new Meeting(LocalTime.of(14, 30), LocalTime.of(15, 0));
        Meeting meeting7 = new Meeting(LocalTime.of(16, 0), LocalTime.of(17, 0));
        List<Meeting> meetingList2 = Arrays.asList(meeting4, meeting5, meeting6, meeting7);
        WorkCalendar calender2 = new WorkCalendar(LocalTime.of(10, 0), LocalTime.of(18, 30), meetingList2);

        MeetingPlanner meetingPlanner = new MeetingPlanner();

        List<Meeting> meeting = meetingPlanner.findFreeSlot(calender1, calender2, Duration.ofMinutes(MEETING_DURATION_MINUTES));
        System.out.println(meeting);
    }
}
