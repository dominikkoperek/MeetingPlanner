package pl.dominik;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class MeetingPlanner {
    /**
     * Finds all possible slots to meet by 2 people calenders based on meeting duration.
     *
     * @param firstPersonCalender  The WorkCalendar of the first person, including their working hours and a list of their meetings.
     * @param secondPersonCalender The WorkCalendar of the second person, including their working hours and a list of their meetings.
     * @param meetingDuration      Planning meet duration time in minutes.
     * @return List of all free slots for meeting by planned meeting duration time.
     */

    public List<Meeting> findFreeSlot(WorkCalendar firstPersonCalender, WorkCalendar secondPersonCalender,
                                      Duration meetingDuration) {
        Meeting possibleHours = findWorkingHoursRange(firstPersonCalender, secondPersonCalender);
        List<Meeting> occupiedSlots = findNotAvailableSlots(firstPersonCalender,
                secondPersonCalender, possibleHours);
        return findFreeSlots(occupiedSlots, possibleHours, meetingDuration);
    }

    /**
     * Finds earliest and latest possible time for a meeting based on 2 calendars.
     *
     * @param firstPersonCalender  The WorkCalendar of the first person, including their working hours and a list of their meetings.
     * @param secondPersonCalender The WorkCalendar of the second person, including their working hours and a list of their meetings.
     * @return Meeting object that includes the earliest possible start time and the latest possible end time for a meeting.
     */
    private Meeting findWorkingHoursRange(WorkCalendar firstPersonCalender, WorkCalendar secondPersonCalender) {
        Meeting firstPersonWorkingTime = findWorkingHours(firstPersonCalender);
        Meeting secondPersonWorkingTime = findWorkingHours(secondPersonCalender);
        LocalTime possibleStart = findMinWorkHours(firstPersonWorkingTime, secondPersonWorkingTime);
        LocalTime possibleEnd = findMaxWorkHours(firstPersonWorkingTime, secondPersonWorkingTime);
        return new Meeting(possibleStart, possibleEnd);
    }

    /**
     * Finds all possible slots for a meeting based on 2 calenders and planned meeting duration time.
     *
     * @param occupiedSlots   List of all occupied time slots.
     * @param possibleHours   Earliest and latest possible time for a meeting based on 2 calendars.
     * @param meetingDuration Planning meet duration time in minutes.
     * @return List of meetings that are free time slots based on planned meeting duration time.
     */
    private List<Meeting> findFreeSlots(List<Meeting> occupiedSlots, Meeting possibleHours, Duration meetingDuration) {
        List<Meeting> freeSlot = new ArrayList<>();
        LocalTime startTime = possibleHours.getMeetingStartTime();
        for (Meeting meetingSlot : occupiedSlots) {
            LocalTime meetingStartTime = meetingSlot.getMeetingStartTime();
            if (Duration.between(startTime, meetingStartTime).compareTo(meetingDuration) >= 0) {
                freeSlot.add(new Meeting(startTime, meetingStartTime));
            }
            startTime = meetingSlot.getMeetingFinishTime();
        }
        LocalTime endTime = possibleHours.getMeetingFinishTime();
        if (Duration.between(startTime, endTime).compareTo(meetingDuration) >= 0) {
            freeSlot.add(new Meeting(startTime, endTime));
        }
        return freeSlot;
    }

    /**
     * Finds all meetings in both calendars and filter them by earliest and latest possible time and sort them by the earliest time.
     *
     * @param firstPersonCalender  The WorkCalendar of the first person, including their working hours and a list of their meetings.
     * @param secondPersonCalender The WorkCalendar of the second person, including their working hours and a list of their meetings.
     * @param possibleHours        Earliest and latest possible time for a meeting based on 2 calendars.
     * @return List of meetings that are occupied time slots.
     */
    private List<Meeting> findNotAvailableSlots(WorkCalendar firstPersonCalender, WorkCalendar secondPersonCalender,
                                                Meeting possibleHours) {
        Meeting workHours = new Meeting(possibleHours.getMeetingStartTime(), possibleHours.getMeetingFinishTime());
        List<Meeting> allMeetings = combineMeetings(firstPersonCalender, secondPersonCalender);
        List<Meeting> allNotAvailableSlotsInTimeRange = new ArrayList<>();
        for (Meeting allMeeting : allMeetings) {
            Meeting meeting = new Meeting(allMeeting.getMeetingStartTime()
                    , allMeeting.getMeetingFinishTime());
            if (isMeetingTimeValid(workHours, meeting, allNotAvailableSlotsInTimeRange)) {
                allNotAvailableSlotsInTimeRange.add(meeting);
            }
        }
        allNotAvailableSlotsInTimeRange.sort(new MeetingSortingComparator());
        return allNotAvailableSlotsInTimeRange;
    }

    /**
     * Validates if a meeting time is valid. A meeting time is considered valid if it does not overlap with any other meetings,
     * does not start before the work start time, and does not end after the work end time.
     *
     * @param workHours     The working hours during which meetings can be scheduled.
     * @param meeting       The meeting to be validated.
     * @param meetingsHours The list of existing meetings.
     * @return true if the meeting time is valid, false otherwise.
     */
    private boolean isMeetingTimeValid(Meeting workHours, Meeting meeting, List<Meeting> meetingsHours) {
        return meeting.getMeetingStartTime().isAfter(workHours.getMeetingStartTime())
                || meeting.getMeetingStartTime().equals(workHours.getMeetingStartTime())
                && meeting.getMeetingFinishTime().isBefore(workHours.getMeetingFinishTime())
                && !meetingsHours.contains(meeting);
    }

    /**
     * Combines and sorts the meetings from two calendars.
     * This method merges the list of all meetings from two calendars into one and sorts them by the earliest start time.
     *
     * @param firstPersonCalender  The WorkCalendar of the first person, including their working hours and a list of their meetings.
     * @param secondPersonCalender The WorkCalendar of the second person, including their working hours and a list of their meetings.
     * @return A sorted List of all meetings from both calendars, ordered by the earliest start time.
     */
    private List<Meeting> combineMeetings(WorkCalendar firstPersonCalender, WorkCalendar secondPersonCalender) {
        List<Meeting> allMeetings = new ArrayList<>();
        allMeetings.addAll(firstPersonCalender.getPlannedMeeting());
        allMeetings.addAll(secondPersonCalender.getPlannedMeeting());
        allMeetings.sort(new MeetingSortingComparator());
        return allMeetings;
    }

    /**
     * Finds the common end of work time for two persons.
     * This method compares the end of work time for two persons and returns the earlier one.
     *
     * @param firstPersonWorkingTime  The working hours of the first person.
     * @param secondPersonWorkingTime The working hours of the second person.
     * @return The earlier end of work time between the two persons.
     */

    private LocalTime findMaxWorkHours(Meeting firstPersonWorkingTime, Meeting secondPersonWorkingTime) {
        return firstPersonWorkingTime.getMeetingFinishTime()
                .isBefore(secondPersonWorkingTime.getMeetingFinishTime())
                ? firstPersonWorkingTime.getMeetingFinishTime()
                : secondPersonWorkingTime.getMeetingFinishTime();
    }

    /**
     * Finds the common start of work time for two persons.
     * This method compares the start of work time for two persons and returns the later one.
     *
     * @param firstPersonWorkingTime  The working hours of the first person.
     * @param secondPersonWorkingTime The working hours of the second person.
     * @return The late start of work time between the two persons.
     */

    private LocalTime findMinWorkHours(Meeting firstPersonWorkingTime, Meeting secondPersonWorkingTime) {
        return firstPersonWorkingTime.getMeetingStartTime()
                .isAfter(secondPersonWorkingTime.getMeetingStartTime())
                ? firstPersonWorkingTime.getMeetingStartTime()
                : secondPersonWorkingTime.getMeetingStartTime();
    }

    /**
     * Finds work time from calendar.
     *
     * @param calender The WorkCalendar, including their working hours and a list of their meetings.
     * @return Meeting object that contains work time.
     */
    private Meeting findWorkingHours(WorkCalendar calender) {
        LocalTime workStartTime = calender.getWorkStartTime();
        LocalTime workFinishTime = calender.getWorkFinishTime();
        return new Meeting(workStartTime, workFinishTime);
    }
}
