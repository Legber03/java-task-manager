import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            return true;
        } else {
            System.out.println("Такой задачи нет!");
            return false;
        }
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }


    public boolean completeTask(int index) {
        if(index >= 0 && index < tasks.size()) {
            tasks.get(index).complete();
            return true;
        } else{
            System.out.println("Такой задачи нет!");
            return false;
        }

    }

    public void showTasks() {
        int number = 1;
        if (tasks.isEmpty()) {
            System.out.println("Задач пока нет");
            return;
        }

        for (Task task : tasks) {
            if (task.isCompleted()) {
                System.out.println(number + ". " + task.getTitle() + " [✓]");
            } else {
                System.out.println(number + ". " + task.getTitle() + " [ ]");
            }
            number++;


        }

    }

    public void showTasksNumbers() {

        if (tasks.isEmpty()) {
            System.out.println("Задач пока нет");
            return;
        }

        int number = 1;

        for (Task task : tasks) {
            System.out.println(number + ". " + task.getTitle());
            number++;
        }
    }
}