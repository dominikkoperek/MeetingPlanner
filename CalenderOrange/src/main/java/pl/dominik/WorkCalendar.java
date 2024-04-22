package pl.dominik;

import java.time.LocalTime;
import java.util.List;

public class WorkCalendar {
    private LocalTime workStartTime;
    private LocalTime workFinishTime;
    private List<Meeting> plannedMeeting;

    public WorkCalendar(LocalTime workStartTime, LocalTime workFinishTime, List<Meeting> plannedMeeting) {
        if (workStartTime.isAfter(workFinishTime)) {
            throw new IllegalTimeWorkException();
        }
        this.workStartTime = workStartTime;
        this.workFinishTime = workFinishTime;
        this.plannedMeeting = plannedMeeting;
    }

    public LocalTime getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(LocalTime workStartTime) {
        this.workStartTime = workStartTime;
    }

    public LocalTime getWorkFinishTime() {
        return workFinishTime;
    }

    public void setWorkFinishTime(LocalTime workFinishTime) {
        this.workFinishTime = workFinishTime;
    }

    public List<Meeting> getPlannedMeeting() {
        return plannedMeeting;
    }

    public void setPlannedMeeting(List<Meeting> plannedMeeting) {
        this.plannedMeeting = plannedMeeting;
    }

}