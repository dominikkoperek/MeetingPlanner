package pl.dominik;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkCalendarTest {
    @Test
    public void shouldCreateCalenderThrowException1() {
        //given
        LocalTime workStartTime = LocalTime.of(18, 0);
        LocalTime workEndTime = LocalTime.of(5, 0);
        Meeting meeting1 = new Meeting(LocalTime.of(10, 0), LocalTime.of(11, 0));
        Meeting meeting2 = new Meeting(LocalTime.of(12, 0), LocalTime.of(12, 30));
        Meeting meeting3 = new Meeting(LocalTime.of(13, 0), LocalTime.of(13, 30));
        List<Meeting> meetings = Arrays.asList(meeting1, meeting2, meeting3);

        //when && then
        assertThrows(IllegalTimeWorkException.class, () -> {
            WorkCalendar workCalendar = new WorkCalendar(workStartTime, workEndTime, meetings);
        });
    }
}