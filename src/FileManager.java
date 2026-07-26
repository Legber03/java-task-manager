import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public void saveTasks(ArrayList<Task> tasks) {

        try {
            FileWriter writer = new FileWriter("tasks.txt", StandardCharsets.UTF_8);
            for (Task task : tasks) {
                writer.write(task.getTitle() + "|" + task.isCompleted() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла");

        }


    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> loaded = new ArrayList<>();

        File file = new File("tasks.txt");
        if (!file.exists()) {
            return loaded;
        }

        try {
            Scanner reader = new Scanner(file, StandardCharsets.UTF_8);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split("\\|");
                String title = parts[0];
                String completedText = parts[1];
                boolean completed = Boolean.parseBoolean(completedText);
                Task task = new Task(title);
                if (completed) {
                    task.complete();
                }
                loaded.add(task);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке файла");
        }
        return loaded;
    }
}
