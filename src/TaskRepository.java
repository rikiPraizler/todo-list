import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private List<Task> tasksList = new ArrayList<>();
    private File file = new File("data/todo-list.json");

    public TaskRepository() throws Exception {
        loadTasksFromFile();
    }
    //Loads the tasks from the data file
    public void loadTasksFromFile() throws Exception {
        if (!file.exists()) return;

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
                        tasksList.add(Task.jsonToTask(item));
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Saves the given list of tasks to the JSON data file.
    public void saveTasksToFile() throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("[");
            for (int i = 0; i < tasksList.size(); i++) {
                bw.write(tasksList.get(i).taskToJson());
                if (i != tasksList.size() - 1) bw.write(",");
            }
            bw.write("]");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Adds a new task to the task list.
    public void add(Task t) throws Exception {
        tasksList.add(t);
        saveTasksToFile();
    }

    //Updates task in the task list.
    public void update(Task newT) throws Exception {
        for (Task t : tasksList) {
            if (t.getId() == newT.getId()) {
                t.setTitle(newT.getTitle());
                t.setDescription(newT.getDescription());
                t.setStatus(newT.getStatus());
                break;
            }
        }
        saveTasksToFile();
    }

    //deletes task from the task list.
    public void delete(int id) throws Exception {
        tasksList.removeIf(a -> a.getId() == id);
        saveTasksToFile();
    }

    //Returns task from the task list by ID.
    public Task getById(int id) throws Exception {
        for (Task t : tasksList) {
            if (t.getId() == id) {
                return t;
            }
        }
        throw new Exception("Task with ID " + id + " not found");
    }

    //Returns the task list.
    public List<Task> listAll(){
        return tasksList;
    }

}

