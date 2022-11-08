package courseSecond.schedule.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task {
    private String title; // Заголовок

    private String description; // Описание
    private TypeTask type;
    private int counter = 1; // Счетчик
    private final int id; // Идентификатор
    private final  LocalDateTime taskDateTime;

    public enum TypeTask {
        PERSONAL("Личная задача"),
        WORK("Рабочая задача");

        public static TypeTask findByVoiceCommand(String taskType) {
            for (TypeTask input : values()) {
                if (input.getTask().equals(taskType)) {
                    return input;
                }
            }

            return null;
        }
        private final String taskType;
        TypeTask(String taskType) {
            this.taskType = taskType;
        }

        public String getTask() {
            return taskType;
        }
    }


    public Task(String title, String description, TypeTask type, LocalDateTime taskDateTime) {
        if(title == null && title.isEmpty()
                || description == null && description.isEmpty()
        ){
            throw new IllegalArgumentException("Нужно заполнить данные полностью");
        }
        this.title = title;
        this.description = description;
        this.type = type;
        this.taskDateTime = taskDateTime;
        this.id = counter++;
    }

    public abstract boolean appearsIn(LocalDate date);

    public abstract String getTaskRepeatRule();
    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeTask getType() {
        return type;
    }

    public void setType(TypeTask type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", id=" + id +
                ", taskDateTime=" + taskDateTime +
                '}';
    }
}
