package pl.dominik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

class MeetingTest {

    @Test
    public void shouldCreateMeetingThrowException1() {
        //given
        LocalTime meetingStartTime = LocalTime.of(15, 30);
        LocalTime meetingEndTime = LocalTime.of(12, 30);

        //when && then

        assertThrows(IllegalTimeWorkException.class, () -> {
            Meeting meeting = new Meeting(meetingStartTime, meetingEndTime);
        });
    }

    @Test
    public void shouldCreateMeetingThrowException2() {
        //given
        LocalTime meetingStartTime = LocalTime.of(12, 30);
        LocalTime meetingEndTime = LocalTime.of(12, 30);

        //when && then

        assertThrows(IllegalTimeWorkException.class, () -> {
            Meeting meeting = new Meeting(meetingStartTime, meetingEndTime);
        });
    }

    @Test
    public void shouldCreateMeeting1() {
        //given
        List<Meeting> meetings = new ArrayList<>();
        LocalTime meetingStartTime = LocalTime.of(11, 30);
        LocalTime meetingEndTime = LocalTime.of(12, 30);
        Meeting meeting = new Meeting(meetingStartTime, meetingEndTime);

        //when
        meetings.add(meeting);

        // then
        assertEquals(1,meetings.size());

    }

}