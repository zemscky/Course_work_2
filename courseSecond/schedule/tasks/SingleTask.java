package courseSecond.schedule.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SingleTask extends Task {

    public SingleTask(String title, String description, TypeTask type, LocalDateTime taskDateTime) {
        super(title, description, type, taskDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        LocalDate taskCreationDate = getTaskDateTime().toLocalDate();
        return taskCreationDate.equals(date)
                && (!taskCreationDate.isAfter(date));
    }

    @Override
    public String getTaskRepeatRule() {
        return "Однократная";
    }
}
