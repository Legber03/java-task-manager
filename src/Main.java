
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // В консоли Windows ввод идёт в stdin.encoding (часто cp866), а не в UTF-8
        Charset stdinCharset = Charset.forName(
                System.getProperty("stdin.encoding", Charset.defaultCharset().name())
        );
        Scanner scanner = new Scanner(System.in, stdinCharset);
        TaskManager taskManager = new TaskManager();
        FileManager fileManager = new FileManager();
        ArrayList<Task> loaded = fileManager.loadTasks();
        for (Task task : loaded) {
            taskManager.addTask(task);
        }
        System.out.println("Добро пожаловать в ваш личный такс менеджер!!!");
        boolean running = true;

        while (running) {
            System.out.println("=== Менеджер задач ===\n" +
                    "\n" +
                    "1. Показать задачи\n" +
                    "2. Добавить задачу\n" +
                    "3. Выполнить задачу\n" +
                    "4. Удалить задачу\n" +
                    "5. Выход"


            );
            String userinput = scanner.nextLine();


            switch (userinput) {
                case "2" -> {
                    System.out.println("Введите название задачи:");
                    String title = scanner.nextLine();
                    Task task = new Task(title);
                    taskManager.addTask(task);
                    System.out.println("Задача добавлена!");
                    fileManager.saveTasks(taskManager.getTasks());
                }
                case "1" -> {
                    taskManager.showTasks();

                }
                case "3" -> {
                    System.out.println("Введите номер выполненной задачи");
                    taskManager.showTasksNumbers();
                    boolean valid = false;
                    while (!valid) {
                        try {
                            int choice = Integer.parseInt(scanner.nextLine());
                            if (taskManager.completeTask(choice - 1)) {
                                valid = true;
                                System.out.println("Задача выполнена!");
                                fileManager.saveTasks(taskManager.getTasks());
                            }


                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: нужно ввести число");
                        }
                    }
                }
                case "4" -> {

                    System.out.println("Введите задачи для удаления");

                    taskManager.showTasksNumbers();
                    boolean valid = false;
                    while (!valid) {
                        try {
                            int choice = Integer.parseInt(scanner.nextLine());
                            if (taskManager.removeTask(choice - 1)) {
                                System.out.println("Задача удалена!");
                                valid = true;
                                fileManager.saveTasks(taskManager.getTasks());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: нужно ввести число");
                        }
                    }


                }
                case "5" -> {
                    System.out.println("До свидания!");
                    running = false;
                }
            }
        }
    }
}
