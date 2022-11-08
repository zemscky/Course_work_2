package courseSecond.schedule.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, String description, TypeTask type, LocalDateTime taskDateTime) {
        super(title, description, type, taskDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        LocalDate taskCreationDate = getTaskDateTime().toLocalDate();
        return taskCreationDate.equals(date)
                && (!taskCreationDate.isAfter(date))
                || (taskCreationDate.isBefore(date.plusWeeks(1)));
    }

    @Override
    public String getTaskRepeatRule() {
        return "Еженедельная";
    }
}
