package courseSecond;

import courseSecond.schedule.Schedule;
import courseSecond.schedule.tasks.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws TaskNotFoundException {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: \n");
                if (scanner.hasNextLine()) {
                    String menu = scanner.nextLine();
                    switch (menu) {
                        case "1":
                            inputTask(taskValues, scanner);
                            break;
                        case "2":
                            removeTask(taskValues, scanner);
                            break;
                        case "3":
                            getDateTask(taskValues, scanner);
                            break;
                        case "0":
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка");
                }
            }
        }
    }

    private static Schedule taskValues = new Schedule();
    private static void inputTask(Schedule taskValues, Scanner scanner) {

        System.out.print("Введите название задачи: ");
        String taskName = scanner.nextLine();
        System.out.println(taskName);

        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();
        System.out.println(taskDescription);

        System.out.print("Введите дату задачи: День.Месяц.Год: ");
        String date = scanner.nextLine();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate taskDate = LocalDate.parse(date,formatterDate);
        try {
            taskDate = LocalDate.parse(date, formatterDate);
            System.out.println(taskDate.format(formatterDate));
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка. Неправильно введена дата");
        }

        System.out.print("Введите время задачи: Часы:Минуты ");
        String time = scanner.nextLine();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime taskTime = LocalTime.parse(time,formatterTime);
        try {
            taskTime = LocalTime.parse(time, formatterTime);
            System.out.println(taskTime.format(formatterTime));
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка. Неправильно введено время");
        }
        LocalDateTime receivedDate = LocalDateTime.of(taskDate, taskTime);

        System.out.print("Выберите тип задачи : \n" +
                "1. Личная задача \n" +
                "2. Рабочая задача \n" +
                "Выберите тип задачи : ");
        int typeTask = scanner.nextInt();
        if (typeTask != 1 && typeTask != 2) {
            throw new RuntimeException("Такого типа задач нет");
        }
        Task.TypeTask type = typeTask == 1 ? Task.TypeTask.PERSONAL : Task.TypeTask.WORK;
        System.out.println(type);


        System.out.print("Выберите периодичность задачи: \n" +
                "1. Однократная \n" +
                "2. Ежедневная \n" +
                "3. Еженедельная \n" +
                "4. Ежемесячная \n" +
                "5. Ежегодная \n" +
                "Выберите периодичность задачи: ");
        int repetitionTask = scanner.nextInt();
        switch (repetitionTask) {
            case 1:
                taskValues.addTask (new SingleTask(taskName, taskDescription, type, receivedDate));
                break;
            case 2:
                taskValues.addTask(new DailyTask(taskName, taskDescription, type, receivedDate));
                break;
            case 3:
                taskValues.addTask(new WeeklyTask(taskName, taskDescription, type, receivedDate));
                break;
            case 4:
                taskValues.addTask(new MonthlyTask(taskName, taskDescription, type, receivedDate));
                break;
            case 5:
                taskValues.addTask(new AnnualTask(taskName, taskDescription, type, receivedDate));
                break;
            default:
                throw new RuntimeException("Такой периодичности нет");
        }

    }
    private static void removeTask(Schedule taskValues, Scanner scanner) throws TaskNotFoundException {
        System.out.println("Введите id задачи, которую нужно удалить: ");
        int id = scanner.nextInt();
        taskValues.removeTask(id);
    }
    private static void getDateTask(Schedule taskValues, Scanner scanner) {
        System.out.print("Введите дату задачи в формате День.Месяц.Год: ");
        scanner.nextLine();
        String date = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dayForTasks = LocalDate.parse(date, formatter);
        printTasksForDays(dayForTasks, taskValues.getTasksForDay(dayForTasks));
    }
    private static void printTasksForDays(LocalDate date, Collection<Task> tasks){
        String dateString = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        if(tasks.size() == 0){
            System.out.println("Нет задачи на: " + dateString);
        }else {
            System.out.println("Задачи на " + dateString);
            for (Task task : tasks) {
                System.out.printf("Название: %s, Время: %s Описание: %s%n",
                        task.getTitle(),
                        task.getTaskDateTime().toLocalTime(),
                        task.getDescription());
            }
        }
    }

    private static void printMenu() {
        System.out.print(
                        " 1. Добавить задачу \n" +
                        " 2. Удалить задачу \n" +
                        " 3. Получить задачу на указанный день \n" +
                        " 0. Выход \n"

        );
    }

}
