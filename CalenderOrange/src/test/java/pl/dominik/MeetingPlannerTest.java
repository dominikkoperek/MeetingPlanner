package pl.dominik;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeetingPlannerTest {
    @Test
    public void shouldCalculateFreeSlotsCorrectlyMeeting30min1() {
        //given
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
        List<Meeting> meetingList2 = Arrays.asList(meeting4, meeting5, meeting6,meeting7);
        WorkCalendar calender2 = new WorkCalendar(LocalTime.of(10, 0), LocalTime.of(18, 30), meetingList2);

        MeetingPlanner meetingCreator = new MeetingPlanner();

        Meeting resultMeeting1 = new Meeting(LocalTime.of(11,30),LocalTime.of(12,0));
        Meeting resultMeeting2 = new Meeting(LocalTime.of(15,0),LocalTime.of(16,0));
        Meeting resultMeeting3 = new Meeting(LocalTime.of(18,0),LocalTime.of(18,30));
        List<Meeting> resultFreeSlots = Arrays.asList(resultMeeting1,resultMeeting2,resultMeeting3);

        // when
        List<Meeting> meeting = meetingCreator.findFreeSlot(calender1, calender2, Duration.ofMinutes(30));
        //then
        assertEquals(resultFreeSlots, meeting);
    }

    @Test
    public void shouldCalculateFreeSlotsCorrectlyMeeting30min2() {
        //given

        //First Person
        Meeting meeting1 = new Meeting(LocalTime.of(9, 0), LocalTime.of(9, 30));
        Meeting meeting2 = new Meeting(LocalTime.of(12, 0), LocalTime.of(14, 0));
        Meeting meeting3 = new Meeting(LocalTime.of(14, 30), LocalTime.of(16, 0));
        List<Meeting> meetingList1 = Arrays.asList(meeting1, meeting2, meeting3);
        WorkCalendar calender1 = new WorkCalendar(LocalTime.of(8, 0), LocalTime.of(16, 0), meetingList1);

        //Second Person
        Meeting meeting4 = new Meeting(LocalTime.of(10, 0), LocalTime.of(10, 30));
        Meeting meeting5 = new Meeting(LocalTime.of(11, 30), LocalTime.of(12, 0));
        Meeting meeting6 = new Meeting(LocalTime.of(12, 0), LocalTime.of(14, 0));
        Meeting meeting7 = new Meeting(LocalTime.of(14, 0), LocalTime.of(14, 30));
        List<Meeting> meetingList2 = Arrays.asList(meeting4, meeting5, meeting6,meeting7);
        WorkCalendar calender2 = new WorkCalendar(LocalTime.of(10, 0), LocalTime.of(14, 30), meetingList2);

        MeetingPlanner meetingCreator = new MeetingPlanner();

        Meeting resultMeeting1 = new Meeting(LocalTime.of(10,30),LocalTime.of(11,30));
        List<Meeting> resultFreeSlots = Arrays.asList(resultMeeting1);

        // when
        List<Meeting> meeting = meetingCreator.findFreeSlot(calender1, calender2, Duration.ofMinutes(30));
        //then
        assertEquals(resultFreeSlots, meeting);
    }
    @Test
    public void shouldCalculateFreeSlotsCorrectlyMeeting30min3() {
        //given
        //First Person
        Meeting meeting1 = new Meeting(LocalTime.of(8, 0), LocalTime.of(9, 0));
        Meeting meeting2 = new Meeting(LocalTime.of(12, 0), LocalTime.of(13, 0));
        Meeting meeting3 = new Meeting(LocalTime.of(14, 30), LocalTime.of(15, 0));
        List<Meeting> meetingList1 = Arrays.asList(meeting1, meeting2, meeting3);
        WorkCalendar calender1 = new WorkCalendar(LocalTime.of(8, 0), LocalTime.of(16, 0), meetingList1);

        //Second Person
        Meeting meeting4 = new Meeting(LocalTime.of(10, 0), LocalTime.of(10, 30));
        Meeting meeting5 = new Meeting(LocalTime.of(11, 30), LocalTime.of(12, 0));
        Meeting meeting6 = new Meeting(LocalTime.of(13, 0), LocalTime.of(15, 0));
        Meeting meeting7 = new Meeting(LocalTime.of(15, 0), LocalTime.of(15, 30));
        List<Meeting> meetingList2 = Arrays.asList(meeting4, meeting5, meeting6,meeting7);
        WorkCalendar calender2 = new WorkCalendar(LocalTime.of(12, 0), LocalTime.of(18, 0), meetingList2);

        MeetingPlanner meetingCreator = new MeetingPlanner();

        Meeting resultMeeting1 = new Meeting(LocalTime.of(15,30),LocalTime.of(16,0));
        List<Meeting> resultFreeSlots = Arrays.asList(resultMeeting1);

        // when
        List<Meeting> meeting = meetingCreator.findFreeSlot(calender1, calender2, Duration.ofMinutes(30));
        //then
        assertEquals(resultFreeSlots, meeting);
    }
    @Test
    public void shouldCalculateFreeSlotsCorrectlyMeeting45min() {
        //given

        //First Person
        Meeting meeting1 = new Meeting(LocalTime.of(8, 0), LocalTime.of(9, 0));
        Meeting meeting2 = new Meeting(LocalTime.of(13, 0), LocalTime.of(14, 0));
        Meeting meeting3 = new Meeting(LocalTime.of(14, 30), LocalTime.of(16, 0));
        List<Meeting> meetingList1 = Arrays.asList(meeting1, meeting2, meeting3);
        WorkCalendar calender1 = new WorkCalendar(LocalTime.of(8, 0), LocalTime.of(16, 0), meetingList1);

        //Second Person
        Meeting meeting4 = new Meeting(LocalTime.of(10, 0), LocalTime.of(11, 30));
        Meeting meeting5 = new Meeting(LocalTime.of(13, 30), LocalTime.of(14, 0));
        List<Meeting> meetingList2 = Arrays.asList(meeting4, meeting5);
        WorkCalendar calender2 = new WorkCalendar(LocalTime.of(12, 0), LocalTime.of(18, 0), meetingList2);

        MeetingPlanner meetingCreator = new MeetingPlanner();

        Meeting resultMeeting1 = new Meeting(LocalTime.of(12,0),LocalTime.of(13,0));
        List<Meeting> resultFreeSlots = Arrays.asList(resultMeeting1);

        // when
        List<Meeting> meeting = meetingCreator.findFreeSlot(calender1, calender2, Duration.ofMinutes(45));
        //then
        assertEquals(resultFreeSlots, meeting);
    }
    @Test
    public void shouldCalculateFreeSlotsCorrectlyMeeting15min1() {
        //given

        //First Person
        Meeting meeting1 = new Meeting(LocalTime.of(8, 0), LocalTime.of(11, 15));
        Meeting meeting2 = new Meeting(LocalTime.of(12, 30), LocalTime.of(14, 0));
        Meeting meeting3 = new Meeting(LocalTime.of(14, 30), LocalTime.of(15, 30));
        List<Meeting> meetingList1 = Arrays.asList(meeting1, meeting2, meeting3);
        WorkCalendar calender1 = new WorkCalendar(LocalTime.of(8, 0), LocalTime.of(16, 0), meetingList1);

        //Second Person
        Meeting meeting4 = new Meeting(LocalTime.of(8, 0), LocalTime.of(11, 15));
        Meeting meeting5 = new Meeting(LocalTime.of(12, 30), LocalTime.of(14, 0));
        Meeting meeting6 = new Meeting(LocalTime.of(14, 30), LocalTime.of(15, 30));
        List<Meeting> meetingList2 = Arrays.asList(meeting4, meeting5, meeting6);
        WorkCalendar calender2 = new WorkCalendar(LocalTime.of(8, 0), LocalTime.of(16, 0), meetingList2);

        MeetingPlanner meetingCreator = new MeetingPlanner();

        Meeting resultMeeting1 = new Meeting(LocalTime.of(11,15),LocalTime.of(12,30));
        Meeting resultMeeting2 = new Meeting(LocalTime.of(14,0),LocalTime.of(14,30));
        Meeting resultMeeting3 = new Meeting(LocalTime.of(15,30),LocalTime.of(16,0));
        List<Meeting> resultFreeSlots = Arrays.asList(resultMeeting1,resultMeeting2,resultMeeting3);

        // when
        List<Meeting> meeting = meetingCreator.findFreeSlot(calender1, calender2, Duration.ofMinutes(15));
        //then
        assertEquals(resultFreeSlots, meeting);
    }
    @Test
    public void shouldCalculateFreeSlotsCorrectlyMeeting60min1() {
        //given

        //First Person
        Meeting meeting1 = new Meeting(LocalTime.of(12, 0), LocalTime.of(13, 30));
        Meeting meeting2 = new Meeting(LocalTime.of(14, 30), LocalTime.of(16, 0));
        List<Meeting> meetingList1 = Arrays.asList(meeting1, meeting2);
        WorkCalendar calender1 = new WorkCalendar(LocalTime.of(12, 0), LocalTime.of(16, 0), meetingList1);

        //Second Person
        Meeting meeting4 = new Meeting(LocalTime.of(8, 0), LocalTime.of(11, 15));
        Meeting meeting5 = new Meeting(LocalTime.of(12, 30), LocalTime.of(14, 0));
        Meeting meeting6 = new Meeting(LocalTime.of(14, 30), LocalTime.of(15, 30));
        List<Meeting> meetingList2 = Arrays.asList(meeting4, meeting5, meeting6);
        WorkCalendar calender2 = new WorkCalendar(LocalTime.of(8, 0), LocalTime.of(16, 0), meetingList2);

        MeetingPlanner meetingCreator = new MeetingPlanner();

        Meeting resultMeeting1 = new Meeting(LocalTime.of(11,15),LocalTime.of(12,30));
        List<Meeting> resultFreeSlots = new ArrayList<>();

        // when
        List<Meeting> meeting = meetingCreator.findFreeSlot(calender1, calender2, Duration.ofMinutes(60));
        //then
        assertEquals(resultFreeSlots, meeting);
    }

}