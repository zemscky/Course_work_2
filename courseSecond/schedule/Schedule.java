package courseSecond.schedule;

import courseSecond.TaskNotFoundException;
import courseSecond.schedule.tasks.Task;

import java.time.LocalDate;
import java.util.*;

public class Schedule {
    private final Map<Integer, Task> taskMap = new HashMap<>();

    public void addTask(Task task) {
        taskMap.put(task.getId(),task);
    }

    public Collection<Task> getAllTasks() {
        return taskMap.values();
    }

    public void removeTask(int id) throws TaskNotFoundException {
        if(!taskMap.containsKey(id)) {
            throw new TaskNotFoundException("Такой задачи нет");
        }
        taskMap.remove(id);
    }

    public Collection<Task> getTasksForDay(LocalDate localDate){
       Set<Task> tasksForDays = new TreeSet<>(new TaskTimeComparator());
        for (Task task: taskMap.values()){
            if(task.appearsIn(localDate)){
                tasksForDays.add(task);
            }
        }
        return tasksForDays;
    }
}

