import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    //Loads the tasks from the data file
    private List<Task> loadTasksFromFile() throws Exception {
        List<Task> tasks = new ArrayList<>();
        File file = new File("../data/todo-list.json");
        if (!file.exists()) return tasks;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }

            String content = sb.toString();
            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1);
                if (!content.isEmpty()) {
                    String[] items = content.split("\\},\\{");
                    for (int i = 0; i < items.length; i++) {
                        String item = items[i];
                        if (i != 0) item = "{" + item;
                        if (i != items.length - 1) item = item + "}";
                        tasks.add(Task.jsonToTask(item));
                    }
                }
            }

        } catch (Exception e) {
            throw e;
        }

        return tasks;
    }

    //Adds a new task to the task list.
    public void add(Task t) throws Exception {
        List<Task> tasks = loadTasksFromFile();
        tasks.add(t);

        File file = new File("../data/todo-list.json");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("[");
            for (int i = 0; i < tasks.size(); i++) {
                bw.write(tasks.get(i).taskToJson());
                if (i != tasks.size() - 1) bw.write(",");
            }
            bw.write("]");
        } catch (Exception e) {
            throw e;
        }
    }

    //Updates task in the task list.
    public void update(Task newT) throws Exception {
        List<Task> tasks = loadTasksFromFile();
        for (Task t : tasks) {
            if (t.getId() == newT.getId()) {
                t.setTitle(newT.getTitle());
                t.setDescription(newT.getDescription());
                t.setStatus(newT.getStatus());
                break;
            }
        }
        File file = new File("../data/todo-list.json");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("[");
            for (int i = 0; i < tasks.size(); i++) {
                bw.write(tasks.get(i).taskToJson());
                if (i != tasks.size() - 1) bw.write(",");
            }
            bw.write("]");
        } catch (Exception e) {
            throw e;
        }
    }

    //Updates task in the task list.
    public void delete(Task t) throws Exception {
        List<Task> tasks = loadTasksFromFile();
        tasks.removeIf(a -> a.getId() == t.getId());

        File file = new File("../data/todo-list.json");
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("[");
            for (int i = 0; i < tasks.size(); i++) {
                bw.write(tasks.get(i).taskToJson());
                if (i != tasks.size() - 1) bw.write(",");
            }
            bw.write("]");
        } catch (
                Exception e) {
            throw e;
        }
    }

}

