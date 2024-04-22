package pl.dominik;

import java.util.Comparator;

public class MeetingSortingComparator implements Comparator<Meeting> {
    @Override
    public int compare(Meeting o1, Meeting o2) {
        int compareStartTime = o1.getMeetingStartTime().compareTo(o2.getMeetingStartTime());
        if (compareStartTime != 0) {
            return compareStartTime;
        } else {
            return o1.getMeetingFinishTime().compareTo(o2.getMeetingFinishTime());
        }
    }
}
