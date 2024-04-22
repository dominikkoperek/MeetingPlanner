package pl.dominik;

import java.time.LocalTime;
import java.util.Objects;

public class Meeting {
    private LocalTime meetingStartTime;
    private LocalTime meetingFinishTime;

    public Meeting(LocalTime meetingStartTime, LocalTime meetingFinishTime) throws IllegalTimeWorkException {
        if (meetingStartTime.isAfter(meetingFinishTime) || meetingStartTime.equals(meetingFinishTime)) {
            throw new IllegalTimeWorkException();
        }
        this.meetingStartTime = meetingStartTime;
        this.meetingFinishTime = meetingFinishTime;
    }

    public LocalTime getMeetingStartTime() {
        return meetingStartTime;
    }

    public void setMeetingStartTime(LocalTime meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    public LocalTime getMeetingFinishTime() {
        return meetingFinishTime;
    }

    public void setMeetingFinishTime(LocalTime meetingFinishTime) {
        this.meetingFinishTime = meetingFinishTime;
    }

    @Override
    public String toString() {
        return "[\"" + meetingStartTime + " - " + meetingFinishTime + "\"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(meetingStartTime, meeting.meetingStartTime) && Objects.equals(meetingFinishTime, meeting.meetingFinishTime);
    }

}
